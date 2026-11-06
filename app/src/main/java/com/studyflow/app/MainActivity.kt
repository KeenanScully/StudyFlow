package com.studyflow.app

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.ComponentActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.core.content.ContextCompat
import com.studyflow.app.navigation.StudyFlowNavGraph
import com.studyflow.app.ui.theme.theme.StudyFlowTheme


class MainActivity : ComponentActivity() {

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            // Permission result will be handled here if needed.
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            StudyFlowTheme {

                LaunchedEffect(Unit) {
                    if (Build.VERSION.SDK_INT >= 33) {

                        val permissionStatus =
                            ContextCompat.checkSelfPermission(
                                this@MainActivity,
                                Manifest.permission.POST_NOTIFICATIONS
                            )

                        if (permissionStatus != PackageManager.PERMISSION_GRANTED) {
                            requestPermissionLauncher.launch(
                                Manifest.permission.POST_NOTIFICATIONS
                            )
                        }
                    }
                }

                StudyFlowNavGraph()
            }
        }
    }
}