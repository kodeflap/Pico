/*
 * Copyright (c) 2023 [kodeflap]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
public fun picoMode(icon: Int, context: Context): PictureInPictureParams? {
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
