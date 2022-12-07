package com.app.exchangerates.presentation.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

// If the device is rebooted, restart the service
class StartReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED && getServiceState(context) == ServiceState.STARTED) {
            Intent(context, ServicesUpdateData::class.java).also {
                it.action = Actions.START.name
                context.startService(it)
            }
        }
    }
}
