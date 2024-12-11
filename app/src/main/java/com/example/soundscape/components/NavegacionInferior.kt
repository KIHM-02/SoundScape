package com.example.soundscape.components

import androidx.compose.foundation.background
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.soundscape.model.Items_bottom_nav.*
import com.example.soundscape.navegation.currentRoute

@Composable
fun NavegacionInferior(
    navController: NavHostController
){
    val menu_items = listOf(
        Item_botton_nav1,
        Item_botton_nav2,
        Item_botton_nav3
    )
    val gradientBrush = Brush.verticalGradient(colors = listOf(Color(0xFF097CBF),Color(0xFF1CA94B)))
    BottomAppBar (containerColor = Color.White, modifier = Modifier.background(gradientBrush)){
        NavigationBar(containerColor = Color.Transparent) {
            menu_items.forEach{item->
                val selected = currentRoute(navController) == item.route
                NavigationBarItem(
                    selected = selected,
                    onClick = {navController.navigate(item.route)},
                    icon = {
                        Icon(imageVector = item.icon, contentDescription = item.title)
                    },
                    label = {
                        Text(text = item.title)
                    }
                )
            }
        }
    }
}