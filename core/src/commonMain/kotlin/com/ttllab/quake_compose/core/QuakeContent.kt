package com.ttllab.quake_compose.core

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.DisposableEffectScope
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import com.ttllab.quake_compose.core.entity.Vector3D

@Composable
fun QuakeContent(
    modifier: Modifier = Modifier,
    rotationEnabled: Boolean = true,
    positionEnabled: Boolean = true,
    content: @Composable () -> Unit,
) {
    val sensorController = remember { SensorController() }

    DisposableEffect(Unit) {
        sensorController.start()

        onDispose {
            sensorController.stop()
        }
    }

    QuakeContentBox(
        content = content,
        rotationEnabled = rotationEnabled,
        positionEnabled = positionEnabled,
        sensorController = sensorController,
        modifier = modifier,
    )
}

@Composable
fun QuakeContentBox(
    content: @Composable () -> Unit,
    rotationEnabled: Boolean = true,
    positionEnabled: Boolean = true,
    sensorController: SensorController,
    modifier: Modifier = Modifier,
) {
    val accelerationValue by sensorController.accelerationValue
    val rotationValue by sensorController.rotationValue

    val rotationZ: Float by animateFloatAsState(rotationValue.z)
    val offsetX: Float by animateFloatAsState(-accelerationValue.x * 2f)
    val offsetY: Float by animateFloatAsState(-accelerationValue.y * 2f)

    Box(
        modifier = modifier
            .graphicsLayer {
                this.rotationZ = if (rotationEnabled) rotationZ else 0f
                this.translationX = if (positionEnabled) offsetX else 0f
                this.translationY = if (positionEnabled) offsetY else 0f
            }
    ) {
        content()
    }
}
