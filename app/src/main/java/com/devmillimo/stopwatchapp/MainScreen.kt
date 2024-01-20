package com.devmillimo.stopwatchapp

import android.hardware.lights.Light
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.devmillimo.stopwatchapp.service.ServiceHelper


@ExperimentalAnimationApi
@Composable
fun Mainscreen(stopwatchService: StopwatchService) {

    val context = LocalContext.current
    val hours by stopwatchService.hours
    val minutes by stopwatchService.minutes
    val seconds by stopwatchService.seconds
    val currentState by stopwatchService.currentState


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface)
            .padding(30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            modifier = Modifier.weight(weight = 9f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedContent(targetState = hours, transitionSpec = { addAnimation() }) {
                Text(
                    text = hours,
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.h1.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = if (hours == "00") Color.White else Color.Blue
                    )
                )


            }
            AnimatedContent(targetState = minutes, transitionSpec = { addAnimation() }) {
                Text(
                    text = minutes, style = TextStyle(
                        fontSize = MaterialTheme.typography.h1.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = if (minutes == "00") Color.White else Color.Blue
                    )
                )
            }

            AnimatedContent(targetState = seconds, transitionSpec = { addAnimation() }) {
                Text(
                    text = seconds,
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.h1.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = if (seconds == "00") Color.White else Color.Blue
                    )
                )

            }
        }

        Row(modifier = Modifier.weight(weight = 1f)) {
            Button(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(0.8f),
                onClick = {
                    ServiceHelper.triggerForegroundService(
                        context = context,
                        action = if (currentState == StopwatchState.Started) ACTION_SERVICE_STOP else ACTION_SERVICE_START
                    )
                }, colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (currentState == StopwatchState.Started) Color.Red else Color.Blue,
                    contentColor = Color.White
                )

                ) {
                Text(text = if (currentState == StopwatchState.Started) "Stop"
                    else if (currentState == StopwatchState.Stopped) "Resume"
                else "Start"
                )
            }
            Spacer(modifier = Modifier.width(30.dp))
            Button(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(0.8f),
                onClick = {
                    ServiceHelper.triggerForegroundService(
                        conetext = context, action = ACTION_SERVICE_RESET
                    )
                },

                enabled = seconds != "00" && currentState != StopwatchState.Started,
                colors = ButtonDefaults.buttonColors(disabledBackgroundColor = Color.LightGray)
            ) {
                Text(text = "Cancel")
            }

        }
    }
}

@ExperimentalAnimationApi
fun addAnimation(duration: Int = 600): ContentTransform{
    return slideInVertically (animationSpec = tween(durationMillis = duration)){
        height -> height } animationSpec  = tween(durationMillis = duration)
}


