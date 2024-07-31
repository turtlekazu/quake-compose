package com.ttllab.quake_compose.core

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.ttllab.quake_compose.core.entity.Vector3D
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.CoreMotion.CMDeviceMotion
import platform.CoreMotion.CMDeviceMotionHandler
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
        if (!sensorManager.isDeviceMotionAvailable()) {
            return
        }
        if (queue == null) {
            return
        }

        val interval = 1.0 / 60.0 // 60Hz
        val coefficient = 50f
        sensorManager.deviceMotionUpdateInterval = interval

        val deviceMotionHandler: CMDeviceMotionHandler =
            { data: CMDeviceMotion?, error: NSError? ->
                data?.let {
                    it.userAcceleration.useContents {
                        accelerationValue.value = Vector3D(
                            x = x.toFloat(),
                            y = y.toFloat(),
                            z = z.toFloat(),
                        ) * coefficient
                    }

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

        sensorManager.startDeviceMotionUpdatesToQueue(queue, deviceMotionHandler)
    }

    actual fun stop() {
        if (sensorManager.deviceMotionActive) {
            sensorManager.stopDeviceMotionUpdates()
        }
    }
}
