package com.example.soundscape.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.soundscape.screens.BuscadorScreen
import com.example.soundscape.screens.HomeScreen
import com.example.soundscape.screens.InicioScreen
import com.example.soundscape.screens.PlaylistsScreen
import com.example.soundscape.view.HomeUser

@Composable
fun AppNavigation(
    navController : NavHostController
){
    NavHost(navController = navController,
        startDestination =  NavScreen.HomeScreen.name) {
        composable(NavScreen.HomeScreen.name){
            HomeScreen()
        }
        composable(NavScreen.Inicio.name){
            InicioScreen()
        }
        composable(NavScreen.Playlist.name){
            PlaylistsScreen()
        }
        composable(NavScreen.Buscardor.name){
            BuscadorScreen()
        }
    }
}