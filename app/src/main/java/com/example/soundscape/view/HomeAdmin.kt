package com.example.soundscape.view

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.soundscape.controller.DataMusic.musicList
import com.example.soundscape.controller.DataMusic.removeMusicByName
import com.example.soundscape.controller.DataUsers.clientList
import com.example.soundscape.controller.DataUsers.removeClientByEmail
import com.example.soundscape.model.Cliente
import com.example.soundscape.model.Music
import com.example.soundscape.ui.theme.SoundScapeTheme
import kotlin.reflect.KClass

class HomeAdmin : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{
                Content()
            }
        }

    @Composable
    fun Content()
    {
        val email = rememberSaveable { mutableStateOf("") }
        val cancion = rememberSaveable { mutableStateOf("") }
        val usuarios = remember { mutableStateListOf<Cliente>() }
        val canciones = remember { mutableStateListOf<Music>() }

        val context = LocalContext.current

        SoundScapeTheme {
            Column(modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .background(
                    Brush
                        .verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.secondary
                            )
                        )
                ),
                    horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(20.dp))

                Text(modifier = Modifier
                        .padding(16.dp),
                    text = "Bienvenido administrador",
                    color = Color.White)

                Spacer(modifier = Modifier.height(20.dp))

                TextField(
                    value = email.value,
                    onValueChange = { query ->
                        email.value = query
                        usuarios.clear()
                        if (query.isNotEmpty()) {
                            usuarios.addAll(
                                clientList.filter {
                                    it.email.contains(query, ignoreCase = true)
                                }
                            )
                        }
                    },
                    label = { Text(
                        "Buscar usuarios",
                        color = Color.Black,
                        fontSize = 20.sp)},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    shape = CircleShape,
                    singleLine = true,
                    maxLines = 1
                )

                LazyColumn (
                    //horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .padding(10.dp)
                        .size(width = 200.dp, height = 250.dp)
                ){
                    items(usuarios){ usuario ->
                        Card(
                            onClick = {
                                try {
                                    val done = removeClientByEmail(usuario.email)
                                    if (done) {
                                        Toast.makeText(
                                            context, "Usuario eliminado",
                                            Toast.LENGTH_SHORT).show()
                                    }
                                } catch (e: Exception) {
                                    Toast.makeText(
                                        context, "Usuario no eliminado",
                                        Toast.LENGTH_SHORT).show()
                                    Log.d("HomeAdmin", "Error al eliminar usuario: $e")
                                }
                            },
                            modifier = Modifier.size(width = 400.dp, height = 50.dp),
                            elevation = CardDefaults.cardElevation(10.dp),
                            border = BorderStroke(1.dp, Color.Cyan),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFF1F3D83)
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(8.dp)
                            ) {
                                Row(
                                    //verticalArrangement = Arrangement.Center
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        usuario.email,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp,
                                        color = Color.White
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    IconButton(
                                        onClick = {
                                            try {
                                                val done = removeClientByEmail(usuario.email)
                                                if (done) {
                                                    Toast.makeText(
                                                        context, "Usuario eliminado",
                                                        Toast.LENGTH_SHORT).show()
                                                }
                                            } catch (e: Exception) {
                                                Toast.makeText(
                                                    context, "Usuario no eliminado",
                                                    Toast.LENGTH_SHORT).show()
                                                Log.d("HomeAdmin", "Error al eliminar usuario: $e")
                                            }
                                        },
                                        modifier = Modifier.size(40.dp)
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_delete_24),
                                            contentDescription = "Eliminar ${usuario.email}",
                                            tint = Color(0xFF1CA94B)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                HorizontalDivider(
                    color = MaterialTheme.colorScheme.tertiary,
                    thickness = 5.dp,
                    modifier = Modifier
                        .padding(10.dp)
                )

                TextField(
                    value = cancion.value,
                    onValueChange = { query ->
                        cancion.value = query
                        canciones.clear()
                        if (query.isNotEmpty()) {
                            canciones.addAll(
                                musicList.filter {
                                    it.nameSong.contains(query, ignoreCase = true)
                                }
                            )
                        }
                    },
                    label = { Text(
                        "Buscar canciones",
                        color = Color.Black,
                        fontSize = 20.sp)},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    shape = CircleShape,
                    singleLine = true,
                    maxLines = 1
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.padding(10.dp)
                ) {
                    items(canciones) { music ->
                        Card(
                            onClick = { removeMusicByName(music.nameSong) },
                            modifier = Modifier.size(width = 200.dp, height = 100.dp),
                            elevation = CardDefaults.cardElevation(10.dp),
                            border = BorderStroke(1.dp, Color.Cyan),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFF1F3D83)
                            )
                        ) {
                            Row(
                                modifier = Modifier.padding(8.dp)
                            ) {
                                val imageBitmap = BitmapFactory.decodeFile(music.image)
                                Image(
                                    painter = BitmapPainter(imageBitmap.asImageBitmap()),
                                    contentDescription = "Imagen de ${music.nameSong}",
                                    modifier = Modifier.size(80.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Column(
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        music.nameSong,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp,
                                        color = Color.White
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    IconButton(
                                        onClick = { removeMusicByName(music.nameSong) },
                                        modifier = Modifier.size(40.dp)
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_delete_24),
                                            contentDescription = "Borrar ${music.nameSong}",
                                            tint = Color(0xFF1CA94B)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                HorizontalDivider(
                    color = MaterialTheme.colorScheme.tertiary,
                    thickness = 5.dp,
                    modifier = Modifier
                        .padding(10.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        val intent = Intent(context, AddMusic::class.java)
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .padding(3.dp)
                        .fillMaxWidth(),
                    shape = CircleShape,
                    colors = ButtonDefaults.
                        buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
                ){
                    Text(text = "Agregar musica",
                        modifier = Modifier
                            .padding(5.dp),
                        fontSize = 20.sp)
                }

            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewContent()
    {
        Content()
    }


}

