package com.ttllab.quake_compose.core.entity

import androidx.compose.runtime.Stable

@Stable
data class Vector3D(
    val x: Float = 0f,
    val y: Float = 0f,
    val z: Float = 0f,
) {
    operator fun plus(other: Vector3D): Vector3D {
        return Vector3D(
            x = x + other.x,
            y = y + other.y,
            z = z + other.z,
        )
    }
    operator fun minus(other: Vector3D): Vector3D {
        return Vector3D(
            x = x - other.x,
            y = y - other.y,
            z = z - other.z,
        )
    }
    operator fun times(value: Float): Vector3D {
        return Vector3D(
            x = x * value,
            y = y * value,
            z = z * value,
        )
    }
    operator fun div(value: Float): Vector3D {
        return Vector3D(
            x = x / value,
            y = y / value,
            z = z / value,
        )
    }
}
