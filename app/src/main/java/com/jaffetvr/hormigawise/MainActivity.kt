package com.jaffetvr.hormigawise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.jaffetvr.hormigawise.HormigaWise.core.di.AppContainer
import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.di.HormigaWiseModule
import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.presentation.screens.MainHormigaScreen
import com.jaffetvr.hormigawise.ui.theme.HormigaWiseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContainer = AppContainer(this)
        val hormigaWiseModule = HormigaWiseModule(appContainer)

        enableEdgeToEdge()

        setContent {
            HormigaWiseTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    MainHormigaScreen(
                        factory = hormigaWiseModule.provideExchangeViewModelFactory()
                    )
                }
            }
        }
    }
}