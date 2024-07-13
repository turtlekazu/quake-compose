package com.ttllab.quake_compose.core

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.ttllab.quake_compose.core.entity.Context
import com.ttllab.quake_compose.core.entity.Vector3D

actual class SensorController {
    actual var accelerationValue: MutableState<Vector3D> = mutableStateOf(Vector3D())
    actual var rotationValue: MutableState<Vector3D> = mutableStateOf(Vector3D())

    actual fun init(context: Context) {
        // TODO: Implement sensor initialization
    }
}
