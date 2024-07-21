package com.ttllab.quake_compose.core

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.ttllab.quake_compose.core.entity.Vector3D

actual class SensorController {
    actual val accelerationValue: MutableState<Vector3D> = mutableStateOf(Vector3D())
    actual val rotationValue: MutableState<Vector3D> = mutableStateOf(Vector3D())
}
