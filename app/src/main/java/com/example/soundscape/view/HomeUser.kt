package com.example.soundscape.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.soundscape.components.NavegacionInferior
import com.example.soundscape.navegation.AppNavigation
import com.example.soundscape.ui.theme.SoundScapeTheme

class HomeUser : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SoundScapeTheme {
                Surface (
                    modifier = Modifier.fillMaxSize()
                ){
                    MainScreen()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MainScreen(){
        val navController = rememberNavController()
        Scaffold (
            bottomBar = {
                NavegacionInferior(navController)
            }
        ){padding->
            Box(
                modifier = Modifier.padding(padding).fillMaxSize()
            ){
                AppNavigation(navController = navController)
            }

        }
    }
}