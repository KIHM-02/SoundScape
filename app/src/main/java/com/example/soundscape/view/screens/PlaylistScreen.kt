package com.example.soundscape.view.screens

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soundscape.R
import com.example.soundscape.view.Reproductor

@Preview
@Composable
fun PlaylistsScreen(){
    val gradientColorList = listOf(
        Color(0xFF1F3D83),//Azul Marino
        Color(0xFF097CBF),//Azul Cielo
    )
    Box(modifier = Modifier.fillMaxSize()
        .background(brush = backgroundGradient(
            isVerticalGradient = true,
            colors = gradientColorList))
    ){
        Column (
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text("Playlist", fontSize = 38.sp, color = Color.White)
            Spacer(modifier = Modifier.height(15.dp))
            listaPlaylist()
        }
    }
}

@Composable
fun listaPlaylist(){
    Column {
        val gradienteColorList = listOf(
            Color(0xFF1F3D83), //Azul Marino
            Color(0xFF097CBF), //Azul Cielo
            Color(0xFF2AC49E) //Cian
        )
        Image(painter = painterResource(id = R.drawable.portada_musica),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth().height(220.dp))
        Row {
            Icon(imageVector = Icons.Outlined.Search,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.size(35.dp))
            Text("Buscador va aqui", fontSize = 25.sp, color = Color.White)
        }
        LazyColumn (verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items (5) {
                Row (modifier = Modifier.fillMaxWidth()
                        .background(brush = backgroundGradientList(isVerticalGradient = false,
                    colors = gradienteColorList))
                ){
                    Box(modifier = Modifier.weight(1f).padding(5.dp)){
                        Row {
                            Spacer(modifier = Modifier.width(1.dp))
                            Image(painter = painterResource(id = R.drawable.portada_musica),
                                contentDescription = "",
                                modifier = Modifier.size(50.dp).clip(CircleShape))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Cancion",
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White)
                        }
                    }
                    val context = LocalContext.current
                    IconButton(onClick = {
                        val intent = Intent(context, Reproductor::class.java)
                        context.startActivity(intent)
                    }) {
                        Icon(painter = painterResource(id = R.drawable.play_button),
                            contentDescription = "", tint = Color(0xFF1CA94B),
                            modifier = Modifier
                                .background(Color.White, CircleShape)
                                .padding(0.dp)
                                .size(40.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun backgroundGradientList(
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