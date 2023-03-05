package com.kodeflap.pico

import android.app.PendingIntent
import android.app.PictureInPictureParams
import android.app.RemoteAction
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.graphics.drawable.Icon
import android.os.Build
import android.util.Rational
import androidx.annotation.RequiresApi
import com.kodeflap.pico.service.PicoReceiver

/**
 * isPipSupported a variable uses to check RAM of the device. It checks whether it is pip supported version
 */
@RequiresApi(Build.VERSION_CODES.S)
public fun pipMode(icon: Int, context: Context): PictureInPictureParams? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        PictureInPictureParams.Builder()
            .setSourceRectHint(Rect())
            .setAspectRatio(Rational(16, 9))
            .setAutoEnterEnabled(true)
            .setActions(
                listOf(
                    RemoteAction(
                        Icon.createWithResource(
                            context,
                            icon
                        ),
                        "sample",
                        "des",
                        PendingIntent.getBroadcast(
                            context, 0,
                            Intent(context, PicoReceiver::class.java),
                            PendingIntent.FLAG_IMMUTABLE
                        )
                    )
                )
            )
            .build()
    } else {
        TODO("VERSION.SDK_INT < S")
    }
}
