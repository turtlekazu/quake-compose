package com.ttllab.quake_compose.core

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.ttllab.quake_compose.core.entity.Vector3D
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.CoreMotion.CMAccelerometerData
import platform.CoreMotion.CMAccelerometerHandler
import platform.CoreMotion.CMGyroData
import platform.CoreMotion.CMGyroHandler
import platform.CoreMotion.CMMotionManager
import platform.Foundation.NSError
import platform.Foundation.NSOperationQueue

actual class SensorController {
    private val sensorManager = CMMotionManager()
    actual val accelerationValue: MutableState<Vector3D> = mutableStateOf(Vector3D())
    actual val rotationValue: MutableState<Vector3D> = mutableStateOf(Vector3D())
    private val queue = NSOperationQueue.currentQueue()

    @OptIn(ExperimentalForeignApi::class)
    actual fun start() {
        // Check if the sensor is available
        if (!sensorManager.isAccelerometerAvailable()) {
            return
        }
        if (!sensorManager.isGyroAvailable()) {
            return
        }
        if (queue == null) {
            return
        }

        // Set the update interval
        val interval = 1.0 / 60.0 // 60Hz
        sensorManager.accelerometerUpdateInterval = interval
        sensorManager.gyroUpdateInterval = interval

        // Define the sensor handlers
        val accelerometerHandler: CMAccelerometerHandler =
            { data: CMAccelerometerData?, error: NSError? ->
                data?.let {
                    it.acceleration.useContents {
                        accelerationValue.value = Vector3D(
                            x = x.toFloat(),
                            y = y.toFloat(),
                            z = z.toFloat(),
                        )
                    }
                } ?: run {
                    println("Error: $error")
                }
            }

        val gyroHandler: CMGyroHandler = { data: CMGyroData?, error: NSError? ->
            data?.let {
                it.rotationRate.useContents {
                    rotationValue.value = Vector3D(
                        x = x.toFloat(),
                        y = y.toFloat(),
                        z = z.toFloat(),
                    )
                }
            } ?: run {
                println("Error: $error")
            }
        }

        // Start the sensor updates
        sensorManager.startAccelerometerUpdatesToQueue(queue, accelerometerHandler)
        sensorManager.startGyroUpdatesToQueue(queue, gyroHandler)
    }

    actual fun stop() {
        if (sensorManager.accelerometerActive) {
            sensorManager.stopAccelerometerUpdates()
        }
        if (sensorManager.gyroActive) {
            sensorManager.stopGyroUpdates()
        }
    }
}
