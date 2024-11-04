package com.github.kotlin_tryout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect.Companion.cornerPathEffect
import androidx.compose.ui.graphics.PathEffect.Companion.stampedPathEffect
import androidx.compose.ui.graphics.StampedPathEffectStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.github.kotlin_tryout.ui.Theme.KotlinTryoutTheme
import kotlinx.coroutines.delay

class AnimationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinTryoutTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xffcececc)), contentAlignment = Alignment.Center
                    ) {
//                        Greeting()
                        Column {

                            OutlineBorderTextExample("Happy Birthday \n Appu")
//                        AnimatedText(text = "Hello Developer!")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AnimatedText(text: String, fontSize: TextUnit = 24.sp) {
    val visibleStates = remember { mutableStateListOf(*Array(text.length) { false }) }
    val size = LocalConfiguration.current.screenWidthDp

    LaunchedEffect(Unit) {
        text.forEachIndexed { index, _ ->
            delay(100 * index.toLong())
            visibleStates[index] = true
        }
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        text.forEachIndexed { index, char ->
            AnimatedVisibility(
                visible = visibleStates.getOrElse(index) { false },
                enter = fadeIn()
            ) {
                StamperPathEffectText(
                    text = char.toString(),
                    fontSize = fontSize,
                )

            }
        }
    }
}

@Composable
fun StamperPathEffectText(text: String, fontSize: TextUnit) {
    Text(
        text = text,
        fontSize = fontSize,
        style = TextStyle(
            drawStyle = Stroke(
                width = 10.0f,
                pathEffect = stampedPathEffect(
//                    shape = Path().apply {
//                        moveTo(0f, 0f)
//                        lineTo(2f, 1f)
//                        lineTo(20f, 2f)
//                        lineTo(1f, 8f)
//                    },
                    shape = Path().apply {
                        close()
                    },
                    advance = 9f,
                    phase = 8f,
                    style = StampedPathEffectStyle.Morph,
                )
            )
        ).copy(
            color = Color.Red
        )
    )
}



@ExperimentalComposeUiApi
@Composable
fun OutlineBorderText(
    text: String,
    modifier: Modifier = Modifier,
    fillColors: List<Color>,
    borderOutlineColor: Color,
    fontFamily: FontFamily,
    fontSize: TextUnit = TextUnit.Unspecified,
    borderWidth: Float = 0.0f,
    borderOutlineDrawStroke: Stroke = Stroke(width = borderWidth, pathEffect = cornerPathEffect(borderWidth)),
) {
    CompositionLocalProvider(
        LocalTextStyle provides convertStyle(
            LocalTextStyle.current,
            fontSize,
            fontFamily
        )
    ) {
        val style: TextStyle = LocalTextStyle.current
        Box(modifier = modifier) {
            Text(
                text = text,
                color = borderOutlineColor,
                style = style.copy(
                    shadow = null,
                    drawStyle = borderOutlineDrawStroke,
                ),
            )

            Text(
                text = text,
                style = style.copy(
                    brush = Brush.linearGradient(
                        colors = fillColors
                    )
                ),
            )
        }
    }
}

fun convertStyle(style: TextStyle, fontSize: TextUnit, fontFamily: FontFamily): TextStyle =
    style.copy(
        fontSize = fontSize,
        fontFamily = fontFamily,
        lineHeight = 45.sp
    )

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OutlineBorderTextExample(text: String) {
    OutlineBorderText(
        text = text,
        borderOutlineColor = Color(0xFF162422),
        borderWidth = 10.0f,
        fillColors = listOf(Color(0xffde9707),Color(0xFFE91E63)),
        fontFamily = familyRestoCreamy
    )
}
internal val familyRestoCreamy = FontFamily(
    Font(R.font.creamy, FontWeight.Normal),
)

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun Greeting() {
    var atEnd by remember { mutableStateOf(false) }
    var animation = AnimatedImageVector.animatedVectorResource(id = R.drawable.animated_vector)


    val painterFirst = rememberAnimatedVectorPainter(
        animatedImageVector = animation,
        atEnd = atEnd
    )

    LaunchedEffect(Unit) {
        while (true) {
            atEnd = !atEnd
            delay(animation.totalDuration.toLong())
        }
    }

    Column {
        Image(painter = painterFirst, contentDescription = "null")
    }
}