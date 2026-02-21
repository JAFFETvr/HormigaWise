package com.jaffetvr.hormigawise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.presentation.screens.MainHormigaScreen
import com.jaffetvr.hormigawise.ui.theme.HormigaWiseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HormigaWiseTheme {
                MainHormigaScreen()
            }
        }
    }
}