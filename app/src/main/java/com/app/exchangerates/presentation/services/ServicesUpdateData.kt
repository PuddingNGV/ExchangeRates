package com.app.exchangerates.presentation.services

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.IBinder
import android.util.Log
import android.os.PowerManager
import android.os.SystemClock
import com.app.exchangerates.domain.models.CurrencyModelApp
import com.app.exchangerates.domain.repository.RepositoryCurrency
import com.app.exchangerates.presentation.MainScreenActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

@AndroidEntryPoint
class ServicesUpdateData : Service() {
    private var wakeLock: PowerManager.WakeLock? = null
    private var isServiceStarted = false
    @Inject
    lateinit var myRepository: RepositoryCurrency
    private lateinit var currencyModel: List<CurrencyModelApp>

    override fun onCreate() {
        super.onCreate()
        Log.i("Services","onCreate")
        val notification = createNotification()
        startForeground(1, notification)
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.i("Services", "onBind")
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("Services","onStartCommand, startId: $startId")
        if (intent != null) {
            val action = intent.action
            Log.i("Services","action $action")
            when (action) {
                Actions.START.name -> startService()
                Actions.STOP.name -> stopService()
            }
        } else {
            Log.i("Services","null intent")
        }
        return START_STICKY
    }

    override fun onTaskRemoved(rootIntent: Intent) {
        val restartServiceIntent = Intent(applicationContext, ServicesUpdateData::class.java).also {
            it.setPackage(packageName)
        };
        val restartServicePendingIntent: PendingIntent = PendingIntent.getService(this, 1, restartServiceIntent, PendingIntent.FLAG_ONE_SHOT);
        applicationContext.getSystemService(Context.ALARM_SERVICE);
        val alarmService: AlarmManager = applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager;
        alarmService.set(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 1000, restartServicePendingIntent);
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Services","onDestroy")
    }

    private fun startService() {
        if (isServiceStarted) return
        Log.i("Services", "startService")
        isServiceStarted = true
        setServiceState(this, ServiceState.STARTED)

        // we need this lock so our service gets not affected by Doze Mode
        wakeLock =
            (getSystemService(Context.POWER_SERVICE) as PowerManager).run {
                newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "ServicesUpdateData::lock").apply {
                    acquire()
                }
            }

        // we're starting a loop in a coroutine
        GlobalScope.launch(Dispatchers.IO) {
            while (isServiceStarted) {
                launch(Dispatchers.IO) {
                    Log.i("Services","Get Data")
                    val repositoryData = myRepository.getLocalCurrency().toList()[0].data
                    if (!repositoryData.isNullOrEmpty()) {
                        currencyModel = repositoryData
                    }
                    Log.i("Services", currencyModel.toString())
                }
                delay(15000)
            }
            Log.i("Services","End of the loop for the service")
        }
    }

    private fun stopService() {
        Log.i("Services","stopService")
        try {
            wakeLock?.let {
                if (it.isHeld) {
                    it.release()
                }
            }
            stopForeground(STOP_FOREGROUND_DETACH)
            stopSelf()
        } catch (e: Exception) {
            Log.i("Services","exception: ${e.message}")
        }
        isServiceStarted = false
        setServiceState(this, ServiceState.STOPPED)
    }

    //Полностью переписать создание уведомления
    private fun createNotification(): Notification {
        val notificationChannelId = "ENDLESS SERVICE CHANNEL"

        // depending on the Android API that we're dealing with we will have
        // to use a specific method to create the notification
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(
            notificationChannelId,
            "Endless Service notifications channel",
            NotificationManager.IMPORTANCE_HIGH
        ).let {
            it.description = "Endless Service channel"
            it.enableLights(true)
            it.lightColor = Color.RED
            it
        }
        notificationManager.createNotificationChannel(channel)

        val pendingIntent: PendingIntent = Intent(this, MainScreenActivity::class.java).let { notificationIntent ->
            PendingIntent.getActivity(this, 0, notificationIntent, 0)
        }

        val builder: Notification.Builder = Notification.Builder(
            this,
            notificationChannelId
        )

        return builder
            .setContentTitle("Endless Service")
            .setContentText("This is your favorite endless service working")
            .setContentIntent(pendingIntent)
            .setTicker("Ticker text")
            .setPriority(Notification.PRIORITY_HIGH) // for under android 26 compatibility
            .build()
    }
}