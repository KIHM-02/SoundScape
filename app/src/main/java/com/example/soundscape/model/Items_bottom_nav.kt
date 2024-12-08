package com.example.soundscape.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PlaylistAddCheckCircle
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.soundscape.navegation.NavScreen

sealed class Items_bottom_nav (
    val icon: ImageVector,
    val title: String,
    val route: String
){
    object Item_botton_nav1:Items_bottom_nav(
        Icons.Outlined.Home,
        title = "Inicio",
        NavScreen.Inicio.name
    )
    object Item_botton_nav2:Items_bottom_nav(
        Icons.Outlined.PlaylistAddCheckCircle,
        title = "Playlist",
        NavScreen.Playlist.name
    )
    object Item_botton_nav3:Items_bottom_nav(
        Icons.Outlined.Search,
        title = "Buscar",
        NavScreen.Buscardor.name
    )


}