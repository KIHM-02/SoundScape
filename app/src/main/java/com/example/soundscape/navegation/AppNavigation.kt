package com.example.soundscape.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.soundscape.view.screens.BuscadorScreen
import com.example.soundscape.view.screens.HomeScreen
import com.example.soundscape.view.screens.InicioScreen
import com.example.soundscape.view.screens.PlaylistsScreen

@Composable
fun AppNavigation(
    navController : NavHostController
){
    NavHost(navController = navController,
        startDestination =  NavScreen.Inicio.name) {
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