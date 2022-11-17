/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.example.separate_thread_hello_world.presentation

import android.R.attr.duration
import android.hardware.SensorManager
import android.os.Bundle
import android.os.PowerManager
import android.os.PowerManager.WakeLock
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.separate_thread_hello_world.presentation.theme.Separate_thread_hello_worldTheme


class MainActivity : ComponentActivity(){
    private lateinit var filesHandler: FilesHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("0000","MainActivity:onCreate")
        super.onCreate(savedInstanceState)

        filesHandler = FilesHandler(this.filesDir)
        AccelerometerListener(getSystemService(SENSOR_SERVICE) as SensorManager,
                              getSystemService(POWER_SERVICE) as PowerManager,
                                filesHandler)

        setContent {
            WearApp()
        }
    }
}

@Composable
fun WearApp() {
    Separate_thread_hello_worldTheme {
        /* If you have enough items in your list, use [ScalingLazyColumn] which is an optimized
         * version of LazyColumn for wear devices with some added features. For more information,
         * see d.android.com/wear/compose.
         */
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            verticalArrangement = Arrangement.Center
        ) {
            Text("recording :)")
        }
    }
}