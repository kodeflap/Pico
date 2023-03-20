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
