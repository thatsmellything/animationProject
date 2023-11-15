package com.example.animationproject.ui.theme

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.gestures.awaitFirstDown

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.horizontalDrag

import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.unit.IntOffset

import androidx.compose.ui.unit.dp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun GesturesTheme() {
    val rotation = remember { Animatable(0f) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier
                .width(300.dp)
                .height(200.dp)
                .graphicsLayer {
                    rotationY = rotation.value
                }
                .pointerInput(Unit) {
                    coroutineScope {
                        while(true) {
                            // stop animation when they touch the square
                            val pointerId = awaitPointerEventScope {
                                awaitFirstDown().id
                            }
                            rotation.stop()
                            awaitPointerEventScope {
                                horizontalDrag(pointerId) {
                                    launch {
                                        rotation.snapTo(
                                            rotation.value + (it.positionChange().x / 5f)
                                        )
                                        println(rotation.value)
                                    }
                                }

                            }
                            // stopped dragging, start animating again
                            launch {
                                //if the rotation is > -30 then animate to the back of the card
                                if(rotation.value <= -30) {
                                    rotation.animateTo(
                                        -180f,
                                        animationSpec = spring()
                                    )
                                } else if (rotation.value >= -150) {
                                    rotation.animateTo(
                                        0f,
                                        animationSpec = spring()
                                    )
                                }


                            }
                        }
                    }
                },




            color = MaterialTheme.colorScheme.secondary,
            shadowElevation = 8.dp

            ) {

        }
    }


}

fun clamp(value: Float, min: Float = -75f, max: Float = 75f): Float {
    if (value >= max) return max
    if (value <= min) return min
    return value
}

