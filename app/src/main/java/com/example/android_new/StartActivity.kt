package com.example.android_new

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_new.theme.ApprenticeshipTheme
import kotlinx.coroutines.delay
import kotlin.math.min
import kotlin.random.Random

class StartActivity : AppCompatActivity() {
    // 设置Activity的布局内容
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApprenticeshipTheme {
                // 使用主题的颜色作为背景色的Surface
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(red = 213, green = 0, blue = 0, alpha = 255)
                ) {
                    MyComposeOneScreen()
                }
            }
        }
        // 创建一个用于处理主线程消息的Handler对象
        val mainHandler = Handler(Looper.getMainLooper())
        // 设置延迟时间为9000毫秒
        val durationMillis = 5000
        // 在主线程上延迟执行匿名函数，结束应用程序
        mainHandler.postDelayed({
            // 在主线程上延迟执行匿名函数，结束应用程序
            finish()
        }, durationMillis.toLong())
    }
}

@Composable
fun MyComposeOneScreen() {
    // 获取密度信息
    val density = LocalDensity.current
    // 获取背景颜色
    val dotBackground = Color(red = 172, green = 173, blue = 173, alpha = 215)
    // 创建一个带有内边距的Scaffold
    Scaffold { innerPadding ->
        BoxWithConstraints(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // 根据密度获取最大宽度和最小高度
            with(density) {
                val maxWidth = maxWidth
                val maxHeight = maxHeight
                // 遍历50次
                for (i in 0..50) {
                    // 使用mutableStateOf创建state变量
                    var state by remember { mutableStateOf(false) }

                    // 在LaunchedEffect中执行延迟队列，state为true时持续时间为3600毫秒，state为false时持续时间为9000毫秒
                    LaunchedEffect(Unit) {
                        while (true) {
                            delay(Random.nextLong(300, 5000))
                            state = true
                            delay(3600)
                            state = false
                            delay(3600)
                        }
                    }

                    // 使用animateFloatAsState创建animScale变量
                    val animScale by animateFloatAsState(
                        targetValue = if (state) 1f else .75f,
                        animationSpec = tween(
                            durationMillis = 12000,
                            easing = LinearEasing
                        ), label = ""
                    )

                    // 使用animateFloatAsState创建animCenterX变量
                    val animCenterX by animateFloatAsState(
                        targetValue = if (state) .8f else 1f,
                        animationSpec = tween(
                            durationMillis = 4000,
                            easing = FastOutSlowInEasing
                        ), label = ""
                    )

                    // 使用animateFloatAsState创建animCenterY变量
                    val animCenterY by animateFloatAsState(
                        targetValue = if (state) .8f else 1f,
                        animationSpec = tween(
                            durationMillis = 9000,
                            easing = FastOutSlowInEasing
                        ), label = ""
                    )

                    // 记录随机生成的centerX、centerY和radius值
                    val centerX = remember {
                        Random.nextInt(0, maxWidth.toPx().toInt()).toFloat()
                    }
                    val centerY = remember {
                        Random.nextInt(0, maxHeight.toPx().toInt()).toFloat()
                    }
                    val radius = remember {
                        Random.nextInt(16, min(maxWidth.toPx(), minHeight.toPx()).toInt() / 14)
                            .toFloat()
                    }
                    val alpha = remember { (Random.nextInt(10, 85) / 100f) }

                    // 在Canvas中绘制圆形
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        drawCircle(
                            color = dotBackground,
                            center = Offset(
                                x = if (i % 2 != 0) centerX * animCenterX else centerX,
                                y = if (i % 2 == 0) centerY * animCenterY else centerY
                            ),
                            radius = radius * animScale,
                            alpha = alpha
                        )
                    }
                }
            }
        }
        // 创建垂直居中且水平居中的Column
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 使用mutableStateOf创建state变量
            var state by remember { mutableStateOf(false) }

            // 在LaunchedEffect中执行延迟队列，state为true时持续时间为900毫秒，state为false时持续时间为300毫秒
            LaunchedEffect(Unit) {
                while (true) {
                    val startDelay = Random.nextLong(300, 900)
                    delay(startDelay)
                    state = true
                    delay(3000)
                    state = false
                    delay(2000 - startDelay)
                }
            }

            // 使用animateFloatAsState创建animAlpha变量
            val animAlpha by animateFloatAsState(
                targetValue = if (state) 1f else 0f,
                animationSpec = tween(
                    durationMillis = 900,
                    easing = FastOutSlowInEasing
                ), label = ""
            )

            // 使用animateFloatAsState创建animRotation变量
            val animRotation by animateFloatAsState(
                targetValue = if (state) 360f else 0f,
                animationSpec = tween(
                    durationMillis = 900,
                    easing = FastOutSlowInEasing
                ), label = ""
            )

            // 使用animateFloatAsState创建animScale变量
            val animScale by animateFloatAsState(
                targetValue = if (state) 1f else 0f,
                animationSpec = tween(
                    durationMillis = 900,
                    easing = FastOutSlowInEasing
                ), label = ""
            )

            // 在Scaffold中显示Logo图片
            Image(
                modifier = Modifier
                    .size(192.dp)
                    .graphicsLayer {
                        alpha = animAlpha
                        rotationX = animRotation
                        rotationY = animRotation
                        rotationZ = animRotation
                        scaleX = animScale
                        scaleY = animScale
                    },
                painter = painterResource(id = R.drawable.logoimg),
                contentDescription = "Why Not NEWS!"
            )

            // 显示网站的每日新闻标语
            MyAnimatedText(
                text = "Your Daily News, Your Way.",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            )
        }
    }
}

@Composable
fun MyAnimatedText(
    text: String,
    modifier: Modifier = Modifier
) {
    // 创建一行显示文本的Row
    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        // 遍历文本的每个字符
        text.forEach { char ->

            // 使用mutableStateOf创建state变量
            var state by remember { mutableStateOf(false) }

            // 在LaunchedEffect中执行延迟队列，state为true时持续时间为900毫秒，state为false时持续时间为300毫秒
            LaunchedEffect(char) {
                while (true) {
                    val startDelay = Random.nextLong(300, 900)
                    delay(startDelay)
                    state = true
                    delay(4000)
                    state = false
                    delay(3000 - startDelay)
                }
            }

            // 使用animateFloatAsState创建animAlpha变量
            val animAlpha by animateFloatAsState(
                targetValue = if (state) 1f else 0f,
                animationSpec = tween(
                    durationMillis = 900,
                    easing = FastOutSlowInEasing
                ), label = ""
            )

            // 显示当前字符
            Text(
                text = char.toString(),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.graphicsLayer {
                    alpha = animAlpha
                }
            )
        }
    }
}
@Preview
@Composable
fun ComposeOneScreenPreview() {
    ApprenticeshipTheme {
        MyComposeOneScreen()
    }
}
@Preview
@Composable
fun MyAnimatedTextPreview() {
    ApprenticeshipTheme {
        MyAnimatedText(
            text = "Your Daily News, Your Way.",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
        )
    }
}