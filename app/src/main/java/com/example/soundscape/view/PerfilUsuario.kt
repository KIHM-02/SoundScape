package com.example.soundscape.view
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soundscape.R
import com.example.soundscape.ui.theme.SoundScapeTheme
import com.example.soundscape.view.screens.InicioScreen
import com.example.soundscape.view.screens.navigateProfileUser
import com.example.soundscape.view.screens.topIconButtons

class PerfilUsuario : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SoundScapeTheme {
                Surface(
                    modifier = Modifier.wrapContentSize()
                        .padding(top= 35.dp, bottom = 50.dp),
                ){
                   perfilUsuarioView(name = "rels b")
                }
            }
        }
    }
}

@Composable
fun perfilUsuarioView(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val activity = context as? Activity
    val ImageProfile= listOf(
        R.drawable.profilepicture,
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3,
        R.drawable.image4,
        R.drawable.image5,
        R.drawable.image6,
        R.drawable.image7,
        R.drawable.image8,
        R.drawable.image9
    )
    val ImageIcon= listOf(
        R.drawable.imageicon1,
        R.drawable.imageicon2,
        R.drawable.imageicon3,
        R.drawable.imageicon4,
        R.drawable.imageicon5,
        R.drawable.imageicon6,
        R.drawable.imageicon7,
        R.drawable.imageicon8,
        R.drawable.imageicon9,
    )
    val randomImage = ImageProfile.random()
    val randomIcon = ImageIcon.random()
    val scrollState = rememberScrollState()

    Box(modifier = Modifier
        .wrapContentSize()

        .verticalScroll(scrollState)
        .background(Brush
            .verticalGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.primary,
                    MaterialTheme.colorScheme.secondary
                )
            )
        )
    ){Column{
        IconButton(onClick = { activity?.finish() }) {
            Icon(imageVector = Icons.Outlined.ArrowBack,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier
                    .size(68.dp)

            )
        }

            Image(
                painter = painterResource(id= randomIcon ),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop

            )


        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()

        ){
            Image(
                painter = painterResource(id = randomImage),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(1.dp, MaterialTheme.colorScheme.tertiary, CircleShape)
                    .fillMaxWidth()
            )

                Button( onClick = {},
                    modifier = Modifier
                        .padding(top = 10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF32B757)) // Fondo del botón
                 ){

                Text( text = "Editar perfil", color = Color.White )

            }
        }

        HorizontalDivider(
            color = Color.Black,
            thickness = 1.dp,
            modifier = Modifier

        )
        Text(
            text = " ¿Que quieres hacer hoy?",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
            modifier = modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally)

        )
        Button(onClick = {},
            modifier = Modifier
                .padding(top= 10.dp,bottom = 10.dp)
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF32B757)) // Fondo del botón
        ){
            Text(
                text = "Crear playlist",
                color = Color.White,
                modifier = Modifier
                    .padding(10.dp)
            )
        }
        Divider(
            color = Color.Black,
            thickness = 1.dp,
            modifier = Modifier
        )
        Text(
            text = "Lista de canciones",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
            modifier = modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally)
        )
        LazyRow(
            horizontalArrangement = Arrangement.SpaceBetween, // Espacio entre elementos
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        ) {
            items(10) { // 3 elementos en la lista
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
        HorizontalDivider(
            color = Color.Black,
            thickness = 1.dp,
            modifier = Modifier

        )
        Text(
            text = "Lista de playlists",
            style = MaterialTheme.typography.bodyLarge,
            color= Color.White,
            modifier = modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally)

        )
        LazyRow(
            horizontalArrangement = Arrangement.SpaceBetween, // Espacio entre elementos
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(bottom= 10.dp)
        ) {
            items(10) { // 3 elementos en la lista
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
                        )
                    {
                        Image(
                            painter = painterResource(id = R.drawable.portada_musica),
                            contentDescription = ""
                        )
                        Text(
                            "NamePlaylist",
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
}





@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SoundScapeTheme {
        perfilUsuarioView(name = "rels b")
    }
}





