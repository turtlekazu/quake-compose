package com.ttllab.quake_compose.core

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.ttllab.quake_compose.core.entity.AppContext
import com.ttllab.quake_compose.core.entity.Vector3D

actual class SensorController {
    private var sensorManager: SensorManager? = null
    actual val accelerationValue: MutableState<Vector3D> = mutableStateOf(Vector3D())
    actual val rotationValue: MutableState<Vector3D> = mutableStateOf(Vector3D())

    init {
        sensorManager = AppContext.get().getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    actual fun start() {
        val accelerationSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)
        val rotationSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        val accelerationListener = getAccelerationListener()
        val rotationListener = getRotationListener()

        sensorManager?.let { sensor ->
            sensor.registerListener(
                accelerationListener,
                accelerationSensor,
                SensorManager.SENSOR_DELAY_NORMAL,
            )

            sensor.registerListener(
                rotationListener,
                rotationSensor,
                SensorManager.SENSOR_DELAY_NORMAL,
            )
        }
    }

    actual fun stop() {
        sensorManager?.let { sensor ->
            sensor.unregisterListener(getAccelerationListener())
            sensor.unregisterListener(getRotationListener())
        }
    }

    private fun getAccelerationListener(): SensorEventListener {
        val listener =
            object : SensorEventListener {
                override fun onAccuracyChanged(
                    sensor: Sensor?,
                    accuracy: Int,
                ) {}

                override fun onSensorChanged(event: SensorEvent?) {
                    event?.let {
                        if (it.sensor.type == Sensor.TYPE_LINEAR_ACCELERATION) {
                            accelerationValue.value =
                                Vector3D(
                                    x = it.values[0], // Use this value
                                    y = it.values[1], // Use this value
                                    z = it.values[2],
                                )
                        }
                    }
                }
            }
        return listener
    }

    private fun getRotationListener(): SensorEventListener {
        val listener =
            object : SensorEventListener {
                override fun onAccuracyChanged(
                    sensor: Sensor?,
                    accuracy: Int,
                ) {}

                override fun onSensorChanged(event: SensorEvent?) {
                    event?.let {
                        if (it.sensor.type == Sensor.TYPE_GYROSCOPE) {
                            rotationValue.value =
                                Vector3D(
                                    x = it.values[0],
                                    y = it.values[1],
                                    z = it.values[2], // Use this value
                                )
                        }
                    }
                }
            }
        return listener
    }
}
