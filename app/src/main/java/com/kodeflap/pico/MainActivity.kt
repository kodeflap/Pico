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

import android.graphics.Rect
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.VideoView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toAndroidRect
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.viewinterop.AndroidView
import com.kodeflap.pico.ui.theme.PicoTheme

class MainActivity : ComponentActivity() {
    private var picoShape = Rect()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PicoTheme {
                AndroidView(factory = {
                    VideoView(it, null).apply {
                        setVideoURI(Uri.parse("android.resource://$packageName/${R.raw.phone}"))
                        start()
                    }
                },
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned {
                            picoShape = it
                                .boundsInWindow()
                                .toAndroidRect()
                        }
                )
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        picoMode(icon = R.drawable.ic_launcher_foreground, this)?.let {
            enterPictureInPictureMode(it)
        }
    }
}
