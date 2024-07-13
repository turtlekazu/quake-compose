package com.ttllab.quake_compose.core

import androidx.compose.runtime.MutableState
import com.ttllab.quake_compose.core.entity.Context
import com.ttllab.quake_compose.core.entity.Vector3D

expect class SensorController {
    var accelerationValue: MutableState<Vector3D>
    var rotationValue: MutableState<Vector3D>
    fun init(context: Context)
}