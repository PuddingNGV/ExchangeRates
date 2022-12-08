package com.app.exchangerates.presentation.services

import android.app.*
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.os.PowerManager
import android.os.SystemClock
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.asLiveData
import com.app.exchangerates.R
import com.app.exchangerates.domain.models.CurrencyModelApp
import com.app.exchangerates.domain.repository.RepositoryCurrency
import com.app.exchangerates.presentation.MainScreenActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class ServicesUpdateData : LifecycleService() {
    private var wakeLock: PowerManager.WakeLock? = null
    private var isServiceStarted = false
    @Inject
    lateinit var myRepository: RepositoryCurrency
    private val notificationChannelId = "SERVICE_CHANNEL"
    private lateinit var currencyModel: List<CurrencyModelApp>
    private val notificationId = 1
    lateinit var notificationBuilder: NotificationCompat.Builder

    override fun onCreate() {
        super.onCreate()
        Log.i("Services","onCreate")
        val notification = createNotification().build()
        startForeground(notificationId, notification)
    }

    override fun onBind(intent: Intent): IBinder? {
        super.onBind(intent)
        Log.i("Services", "onBind")
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
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
        }
        val restartServicePendingIntent: PendingIntent = PendingIntent.getService(this, 1, restartServiceIntent, PendingIntent.FLAG_ONE_SHOT)
        applicationContext.getSystemService(Context.ALARM_SERVICE)
        val alarmService: AlarmManager = applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmService.set(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 1000, restartServicePendingIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Services","onDestroy")
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun startService() {
        if (isServiceStarted) return
        Log.i("Services", "startService")
        isServiceStarted = true
        setServiceState(this, ServiceState.STARTED)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel(notificationManager)

        // we need this lock so our service gets not affected by Doze Mode
        wakeLock =
            (getSystemService(Context.POWER_SERVICE) as PowerManager).run {
                newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "ServicesUpdateData::lock").apply {
                    acquire()
                }
            }

        val data = myRepository.getLocalCurrency().asLiveData()

        // we're starting a loop in a coroutine
        GlobalScope.launch(Dispatchers.Main) {
            while (isServiceStarted) {
                launch(Dispatchers.Main) {
                    data.observe(this@ServicesUpdateData) { currency ->
                        if (!currency.data.isNullOrEmpty()) {
                            currencyModel = currency.data.sortedBy { it.name }
                            val randomData = currencyModel.random()
                            val notification = notificationBuilder
                                .setContentText("${randomData.name} - ${randomData.value}")
                            notificationManager.notify(notificationId, notification.build())
                            Log.i("Services", "is work")
                        }
                    }
                }
                delay(20000)
            }
        }
            Log.i("Services","End of the loop for the service")
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

    private fun createNotification(): NotificationCompat.Builder {
        val pendingIntent: PendingIntent = Intent(this, MainScreenActivity::class.java).let { notificationIntent ->
            PendingIntent.getActivity(this, 0, notificationIntent, 0)
        }

        notificationBuilder = NotificationCompat.Builder(this, notificationChannelId)
            .setAutoCancel(false)
            .setOngoing(true)
            .setSmallIcon(R.drawable.ic_repeat)
            .setContentText("UPDATE DATA")
            //.setContentText("${random.charCode} - ${random.value}")
            .setContentIntent(pendingIntent)

        return notificationBuilder
    }

    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val notificationChannelName = "CURRENCY"
        val channel = NotificationChannel(notificationChannelId, notificationChannelName, IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(channel)
    }
}