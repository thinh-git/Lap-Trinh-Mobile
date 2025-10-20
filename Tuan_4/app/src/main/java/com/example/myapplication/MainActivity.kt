package com.example.udemydemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.udemydemo.ui.AppNavHost
import com.example.udemydemo.ui.theme.UdemyDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UdemyDemoTheme {
                AppNavHost()
            }
        }
    }
}
