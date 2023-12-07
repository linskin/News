package com.example.android_new

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.android_new.theme.ApprenticeshipTheme

class StartShowActivity : AppCompatActivity() {
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
    }
}