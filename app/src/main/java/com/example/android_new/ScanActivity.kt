package com.example.android_new

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.android_new.theme.ApprenticeshipTheme

class ScanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApprenticeshipTheme {
                ScanScreen { finish() }
            }
        }
    }
}


@Composable
fun ScanScreen(goback:()-> Unit) {
    Surface(modifier = Modifier.fillMaxSize(), color = colorResource(id = R.color.purple_200)) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)) {
            TopAppBar(
                title = {
                    Text(
                        text = "联系我们",
                        color = colorResource(id = R.color.white)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { goback() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                            contentDescription = null,
                        )
                    }
                },
                backgroundColor = colorResource(id = R.color.purple_200),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(60.dp),
                elevation = 0.dp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Spacer(modifier = Modifier.width(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.my_img),
                    contentDescription = null,
                    modifier = Modifier
                       .fillMaxWidth()
                       .size(450.dp)
                )
            }
        }
    }
}

