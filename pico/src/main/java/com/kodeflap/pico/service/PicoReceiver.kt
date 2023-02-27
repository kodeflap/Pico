package com.kodeflap.pico.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class PicoReceiver: BroadcastReceiver() {
    override fun onReceive(contect: Context?, intent: Intent?) {
        println("Clicked")
    }
}
