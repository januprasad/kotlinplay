package com.github.januprasad.canvas_samples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.github.januprasad.canvas_samples.ui.theme.InterviewprepTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InterviewprepTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(title = {
                            Text(text = "SampleApp")
                        })
                    },
                ) { innerPadding ->
                    SampleApp(innerPadding)
                }
            }
        }
    }
}

@Composable
fun SampleApp(innerPadding: PaddingValues) {
    Column(Modifier.padding(innerPadding)) {
        getWeatherApp()
        instagramIcon()
        face()
        bazeiercurves()
    }
}

@Composable
fun bazeiercurves() {
}

@Composable
fun face() {
    val backgroundColor = listOf(Color(0xFF2454ff), Color(0xFF423234))
    val sunColor = listOf(Color(0xFFFFC200), Color(0xFFFFE100))
    Canvas(
        modifier =
            Modifier
                .size(100.dp)
                .padding(16.dp),
    ) {
        val width = size.width
        val height = size.height
        val path =
            Path().apply {
                moveTo(width.times(.76f), height.times(.72f))
                cubicTo(
                    width.times(.93f),
                    height.times(.72f),
                    width.times(.98f),
                    height.times(.41f),
                    width.times(.76f),
                    height.times(.40f),
                )
                cubicTo(
                    width.times(.75f),
                    height.times(.21f),
                    width.times(.35f),
                    height.times(.21f),
                    width.times(.38f),
                    height.times(.50f),
                )
                cubicTo(
                    width.times(.25f),
                    height.times(.50f),
                    width.times(.20f),
                    height.times(.69f),
                    width.times(.41f),
                    height.times(.72f),
                )
                close()
            }
        drawRoundRect(
            brush = Brush.verticalGradient(backgroundColor),
            cornerRadius = CornerRadius(50f, 50f),
        )
        drawCircle(
            brush = Brush.verticalGradient(sunColor),
            radius = width.times(.17f),
            center = Offset(width.times(.25f), height.times(.35f)),
        )
        drawCircle(
            brush = Brush.verticalGradient(sunColor),
            radius = width.times(.17f),
            center = Offset(width.times(.75f), height.times(.35f)),
        )
        drawRoundRect(
            topLeft = Offset(width.times(0.33f), height.times(0.7f)),
            size = Size(width.times(0.33f), height.times(0.05f)),
            brush = Brush.verticalGradient(sunColor),
            cornerRadius = CornerRadius(20f, 20f),
        )
//        drawPath(path = path, color = Color.White.copy(alpha = .90f))
    }
}

@Composable
fun getWeatherApp() {
    val backgroundColor = listOf(Color(0xFF2078EE), Color(0xFF74E6FE))
    val sunColor = listOf(Color(0xFFFFC200), Color(0xFFFFE100))
    Canvas(
        modifier =
            Modifier
                .size(100.dp)
                .padding(16.dp),
    ) {
        val width = size.width
        val height = size.height
        val path =
            Path().apply {
                moveTo(width.times(.76f), height.times(.72f))
                cubicTo(
                    width.times(.93f),
                    height.times(.72f),
                    width.times(.98f),
                    height.times(.41f),
                    width.times(.76f),
                    height.times(.40f),
                )
                cubicTo(
                    width.times(.75f),
                    height.times(.21f),
                    width.times(.35f),
                    height.times(.21f),
                    width.times(.38f),
                    height.times(.50f),
                )
                cubicTo(
                    width.times(.25f),
                    height.times(.50f),
                    width.times(.20f),
                    height.times(.69f),
                    width.times(.41f),
                    height.times(.72f),
                )
                close()
            }
        drawRoundRect(
            brush = Brush.verticalGradient(backgroundColor),
            cornerRadius = CornerRadius(50f, 50f),
        )
        drawCircle(
            brush = Brush.verticalGradient(sunColor),
            radius = width.times(.17f),
            center = Offset(width.times(.35f), height.times(.35f)),
        )
        drawPath(path = path, color = Color.White.copy(alpha = .90f))
    }
}

@Composable
fun instagramIcon() {
    val instaColors = listOf(Color.Yellow, Color.Red, Color.Magenta)
    Canvas(
        modifier =
            Modifier
                .size(100.dp)
                .padding(16.dp),
    ) {
        drawRoundRect(
            brush = Brush.linearGradient(colors = instaColors),
            cornerRadius = CornerRadius(60f, 60f),
            style = Stroke(width = 15f, cap = StrokeCap.Round),
        )
        drawCircle(
            brush = Brush.linearGradient(colors = instaColors),
            radius = 45f,
            style = Stroke(width = 15f, cap = StrokeCap.Round),
        )
        drawCircle(
            brush = Brush.linearGradient(colors = instaColors),
            radius = 13f,
            center = Offset(this.size.width * .80f, this.size.height * 0.20f),
        )
    }
}
