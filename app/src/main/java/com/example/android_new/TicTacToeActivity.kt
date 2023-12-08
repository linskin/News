package com.example.android_new

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.android_new.tictactoe.TicTacToeScreen
import com.example.android_new.tictactoe.TicTacToeSharedPref
import com.example.android_new.tictactoe.TicTacToeViewModel
import com.example.android_new.theme.ApprenticeshipTheme

class TicTacToeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fun goBack() {
            finish()
        }
        setContent {
            ApprenticeshipTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TicTacToeScreen(
                        viewModel = TicTacToeViewModel(sharedPref = TicTacToeSharedPref(context = this)),
                        goBack = ::goBack
                    )
                }
            }
        }
    }
}