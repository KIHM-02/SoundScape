package com.example.soundscape.view
import android.graphics.pdf.models.ListItem
import com.example.soundscape.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CheckboxDefaults.colors
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.graphics.Color

import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.soundscape.ui.theme.SoundScapeTheme
import java.time.format.TextStyle

class PerfilUsuario : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            /*SoundScapeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ){
                   perfilUsuarioView(name = "rels b")
                }
            }
        }*/
    }
}

@Composable
fun perfilUsuarioView(name: String, modifier: Modifier = Modifier) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Brush
            .verticalGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.primary,
                    MaterialTheme.colorScheme.secondary
                )
            )
        )
    ){Column{
        Image(
            painter = painterResource(R.drawable.profilepicture),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .fillMaxWidth()
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(10.dp)
                .height(100.dp)
                .fillMaxWidth()

        ){
            Image(
                painter = painterResource(R.drawable.applogo),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .size(100.dp)
                    .fillMaxWidth()
            )
            OutlinedButton(onClick = {},
                modifier = Modifier
                    .background(Color(0xFF32B757))

            )
            {
                Text(text = "Editar perfil",
                    modifier = Modifier
                        .absolutePadding()
                )
            }
        }
        Divider(
            color = Color.Black,
            thickness = 1.dp,
            modifier = Modifier
                .padding(10.dp)
        )
        Text(
            text = " Que quieres hacer hoy",
            style = MaterialTheme.typography.bodyMedium,
            modifier = modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally)

        )
        OutlinedButton(onClick = {},
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally)
                .background(Color(0xFF32B757))


        ){
            Text(
                text = "Crear playlist",
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
            style = MaterialTheme.typography.bodyMedium,
            modifier = modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally)
        )


        LazyColumn (
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally)

        ){

        }
        Divider(
            color = Color.Black,
            thickness = 1.dp,
            modifier = Modifier
                .padding(10.dp)
        )
        Text(
            text = "Lista de playlists",
            style = MaterialTheme.typography.bodyMedium,
            modifier = modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally)

        )
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


}
