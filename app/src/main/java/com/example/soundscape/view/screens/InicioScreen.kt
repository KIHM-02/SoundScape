package com.example.soundscape.view.screens


import android.content.Context
import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soundscape.R
import com.example.soundscape.view.ProfileUser
import com.example.soundscape.view.Reproductor


@Preview
@Composable
fun InicioScreen() {
    val gradientColorList = listOf(
        Color(0xFF1F3D83), // Azul Marino
        Color(0xFF097CBF)  // Azul Cielo
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = backgroundGradient(
                    isVerticalGradient = true,
                    colors = gradientColorList
                ))
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            item { topIconButtons() } // Agrega los elementos como items
            item { Spacer(modifier = Modifier.height(20.dp)) }
            item {
                Column {
                    Text(
                        "Escuchados recientes",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        listenedSong()
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(20.dp)) }
            item {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        "Agregadas Recientes",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Row {
                        newSongs()
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(20.dp)) }
            item {
                Column {
                    Text(
                        "Artistas Nuevos",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Row {
                        newArtist()
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(20.dp)) }
            item {
                Column {
                    Text(
                        "Nuevos Lanzaminetos",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Row {
                        newReleases()
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(20.dp)) }
            item {
                Column {
                    listenedPlaylist()
                }
            }
        }
    }
}

@Composable
fun backgroundGradient(
    isVerticalGradient : Boolean,
    colors: List<Color>
): Brush{
    val endOffset = if (isVerticalGradient){
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
fun topIconButtons(){
    val context = LocalContext.current
    Row(modifier = Modifier.fillMaxWidth()) {
        IconButton(onClick = {navigateProfileUser(context)}) {
            Icon(imageVector = Icons.Outlined.AccountCircle,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.size(68.dp))
        }
    }
}

@Composable
fun listenedSong(){
    Box(
        contentAlignment = Alignment.Center
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp) // Espacio entre elementos
        ) {
            items(3) { // 3 elementos en la lista
                Card(
                    modifier = Modifier.size(width = 120.dp, height = 160.dp),
                    elevation = CardDefaults.cardElevation(10.dp),
                    border = BorderStroke(1.dp, Color.Cyan),
                    colors = CardDefaults.cardColors(
                        contentColor = Color.Black
                    )
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.portada_musica),
                            contentDescription = ""
                        )
                        Text(
                            "Cancion",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(5.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun newSongs(){
    Card (modifier = Modifier
        .fillMaxWidth()
        .height(160.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        border = BorderStroke(1.dp, Color.Cyan),
        colors = CardDefaults.cardColors(
            contentColor = Color.Black
        )
    ){
        Row (
            modifier = Modifier.fillMaxSize()
        ){
            Image(painter = painterResource(id = R.drawable.portada_musica),
                contentDescription = "")
            Spacer(modifier = Modifier.width(5.dp))
            Column {
                val context = LocalContext.current
                Text(
                    "Cancion",
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    modifier = Modifier.padding(5.dp)
                )
                Spacer(modifier = Modifier.height(60.dp))
                IconButton(onClick = {navigateReproductor(context)}) {
                    Icon(painter = painterResource(id = R.drawable.play_button),
                        contentDescription = "",
                        modifier = Modifier.size(80.dp)
                        )
                }
            }
        }
    }
}

@Composable
fun newArtist(){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp) // Espacio entre elementos
    ) {
        items(3) { // 3 elementos en la lista
            Card(
                modifier = Modifier.size(width = 120.dp, height = 160.dp),
                elevation = CardDefaults.cardElevation(10.dp),
                border = BorderStroke(1.dp, Color.Cyan),
                colors = CardDefaults.cardColors(
                    contentColor = Color.Black
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.portada_musica),
                        contentDescription = ""
                    )
                    Text(
                        "Artista",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(5.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun newReleases(){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp) // Espacio entre elementos
    ) {
        items(3) { // 3 elementos en la lista
            Card(
                modifier = Modifier.size(width = 120.dp, height = 160.dp),
                elevation = CardDefaults.cardElevation(10.dp),
                border = BorderStroke(1.dp, Color.Cyan),
                colors = CardDefaults.cardColors(
                    contentColor = Color.Black
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.portada_musica),
                        contentDescription = ""
                    )
                    Text(
                        "Cancion",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(5.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun listenedPlaylist(){
    Box(modifier = Modifier.fillMaxWidth()) {
        Column {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                elevation = CardDefaults.cardElevation(10.dp),
                border = BorderStroke(1.dp, Color.Cyan),
                colors = CardDefaults.cardColors(contentColor = Color.Black)
            ) {
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.portada_musica),
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Column {
                        val context = LocalContext.current
                        Text(
                            "Playlist",
                            fontWeight = FontWeight.Bold,
                            fontSize = 26.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In condimentum")
                        Spacer(modifier = Modifier.height(31.dp))
                        IconButton(onClick = { navigateReproductor(context) }) {
                            Icon(
                                painter = painterResource(id = R.drawable.play_button),
                                contentDescription = "",
                                modifier = Modifier.size(80.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

fun navigateReproductor(context: Context){
    val intent = Intent(context, Reproductor::class.java)
    context.startActivity(intent)
}

fun navigateProfileUser(context: Context) {
    val intent = Intent(context, ProfileUser::class.java)
    context.startActivity(intent)
}