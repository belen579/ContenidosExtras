

package com.example.gpsmaps.conworkmanager

import android.Manifest
import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.gpsmaps.currentLocation.PermissionBox
import com.example.platform.location.bglocationaccess.BgLocationWorker
import java.util.concurrent.TimeUnit


//@Sample(
//    name = "Location - Background Location updates",
//    description = "This Sample demonstrate how to access location and get location updates when app is in background",
//    documentation = "https://developer.android.com/training/location/background",
//)
@Preview(showSystemUi = true)
@Composable
fun BgLocationAccessScreen() {
    // Request for foreground permissions first
    PermissionBox(
        permissions = listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
        ),
        requiredPermissions = listOf(Manifest.permission.ACCESS_COARSE_LOCATION),
        onGranted = {
            // From Android 10 onwards request for background permission only after fine or coarse is granted
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                PermissionBox(permissions = listOf(Manifest.permission.ACCESS_BACKGROUND_LOCATION)) {
                    BackgroundLocationControls()
                }
            } else {
                BackgroundLocationControls()
            }
        },
    )
}


@Composable
private fun BackgroundLocationControls() {
    val context = LocalContext.current
    val workManager = WorkManager.getInstance(context)

    // Component UI state holder
    data class ControlsState(val text: String, val action: String, val onClick: () -> Unit)

    // Observe the worker state to show enable/disable UI
    val workerState by workManager.getWorkInfosForUniqueWorkLiveData(BgLocationWorker.workName)
        .observeAsState()

    val controlsState = remember(workerState) {
        // Find if there is any enqueued or running worker and provide UI state
        val enqueued = workerState?.find { !it.state.isFinished } != null
        if (enqueued) {
            ControlsState(
                text = "Check the logcat for location updates every 15 min",
                action = "Disable updates",
                onClick = {
                    workManager.cancelUniqueWork(BgLocationWorker.workName)
                },
            )
        } else {
            ControlsState(
                text = "Enable location updates and bring the app in the background.",
                action = "Enable updates",
                onClick = {
                    // Schedule a periodic worker to check for location every 15 min
                    workManager.enqueueUniquePeriodicWork(
                        BgLocationWorker.workName,
                        ExistingPeriodicWorkPolicy.KEEP,
                        PeriodicWorkRequestBuilder<BgLocationWorker>(
                            15,
                            TimeUnit.MINUTES,
                        ).build(),
                    )
                },
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = controlsState.text)
        Button(onClick = controlsState.onClick) {
            Text(text = controlsState.action)
        }
    }
}
