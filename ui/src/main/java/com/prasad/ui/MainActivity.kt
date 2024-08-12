package com.prasad.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.prasad.ui.navigation.NavGraph
import com.prasad.ui.ui.theme.CartoonAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            rememberNavController().run {
                CartoonAppTheme {
                    NavGraph(navHostController = this)
                }
            }
        }
    }
}