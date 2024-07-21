package com.ttllab.quake_compose.core

import androidx.compose.runtime.MutableState
import com.ttllab.quake_compose.core.entity.Vector3D

expect class SensorController() {
    val accelerationValue: MutableState<Vector3D>
    val rotationValue: MutableState<Vector3D>
}
