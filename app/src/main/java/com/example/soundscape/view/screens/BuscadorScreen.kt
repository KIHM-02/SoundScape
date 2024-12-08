package com.example.soundscape.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.text.contains

@Preview
@Composable
fun BuscadorScreen() {
    val gradientColorList = listOf(
        Color(0xFF1F3D83), // Azul Marino
        Color(0xFF097CBF)  // Azul Cielo
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = backgroundGradient(
                    isVerticalGradient = true,
                    colors = gradientColorList
                )
            ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        searchBar()
    }

}

@Composable
fun searchBar()
{
    val canciones = remember { mutableStateListOf("Cancion 1", "Cancion 2", "Cancion 3", "Otra Cancion") } // Lista de canciones original
    val textoBusqueda = remember { mutableStateOf("") }
    val cancionesFiltradas = canciones.filter { it.contains(textoBusqueda.value, ignoreCase = true) } // Lista de canciones filtradas


    TextField(
        value = textoBusqueda.value,
        onValueChange = { textoBusqueda.value = it },
        label = { Text("Buscar canciones", color = Color.Black) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(Color.White)
    )
    LazyColumn {
        items(cancionesFiltradas) { cancion ->
            Text(cancion, modifier = Modifier.padding(16.dp))
        }
    }
}