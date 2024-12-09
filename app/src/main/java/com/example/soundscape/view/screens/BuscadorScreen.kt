package com.example.soundscape.view

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soundscape.R
import com.example.soundscape.controller.DataMusic
import com.example.soundscape.model.Music

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
fun backgroundGradient(
    isVerticalGradient: Boolean,
    colors: List<Color>
): Brush {
    val endOffset = if (isVerticalGradient) {
        Offset(0f, Float.POSITIVE_INFINITY)
    } else {
        Offset(Float.POSITIVE_INFINITY, 0f)
    }

    return Brush.linearGradient(
        colors = colors,
        start = Offset.Zero,
        end = endOffset
    )
}

@Composable
fun searchBar() {
    val musicList = DataMusic.musicList
    val textoBusqueda = remember { mutableStateOf("") }
    val cancionesFiltradas = remember { mutableStateListOf<Music>() }

    val context = LocalContext.current

    TextField(
        value = textoBusqueda.value,
        onValueChange = { query ->
            textoBusqueda.value = query
            cancionesFiltradas.clear()
            if (query.isNotEmpty()) {
                cancionesFiltradas.addAll(
                    musicList.filter {
                        it.nameSong.contains(query, ignoreCase = true)
                    }
                )
            }
        },
        label = { Text("Buscar canciones", color = Color.Black) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(Color.White)
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(10.dp)
    ) {
        items(cancionesFiltradas) { cancion ->
            Card(
                onClick = { navigateToPlayer(context, cancion.uri) },
                modifier = Modifier.size(width = 200.dp, height = 160.dp),
                elevation = CardDefaults.cardElevation(10.dp),
                border = BorderStroke(1.dp, Color.Cyan),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF1F3D83)
                )
            ) {
                Row(
                    modifier = Modifier.padding(8.dp)
                ) {
                    val imageBitmap = BitmapFactory.decodeFile(cancion.image)
                    Image(
                        painter = BitmapPainter(imageBitmap.asImageBitmap()),
                        contentDescription = "Imagen de ${cancion.nameSong}",
                        modifier = Modifier.size(80.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            cancion.nameSong,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        IconButton(
                            onClick = { navigateToPlayer(context, cancion.uri) },
                            modifier = Modifier.size(40.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.play_button),
                                contentDescription = "Reproducir ${cancion.nameSong}",
                                tint = Color(0xFF1CA94B)
                            )
                        }
                    }
                }
            }
        }
    }
}

fun navigateToPlayer(context: Context, uri: String) {
    val intent = Intent(context, Reproductor::class.java).apply {
        putExtra("URI", uri)
    }
    context.startActivity(intent)
}
