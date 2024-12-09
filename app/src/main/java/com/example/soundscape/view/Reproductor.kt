
package com.example.soundscape.view
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.soundscape.R
import com.example.soundscape.model.Music
import com.example.soundscape.ui.theme.SoundScapeTheme
import com.example.soundscape.ui.theme.Typography



class Reproductor : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val position = intent.getIntExtra("position", 0)
        val songs = intent.getSerializableExtra("songs") as? ArrayList<Music> ?: arrayListOf()
        setContent {
            SoundScapeTheme {
                ReproductorScreen(songs , position)
            }
        }
    }
}

@Composable
fun ReproductorScreen(songs: List<Music>, currentPosition: Int) {
    var currentSongIndex by remember { mutableStateOf(currentPosition) }
    var isPlaying by remember { mutableStateOf(false) }
    var progress by remember { mutableStateOf(0f) }
    val currentSong = songs[currentSongIndex]
    val totalDuration = 300f // Duración total en segundos

    // Simula el progreso de la canción
    LaunchedEffect(isPlaying) {
        while (isPlaying && progress < totalDuration) {
            kotlinx.coroutines.delay(1000L) // Espera 1 segundo
            progress += 1f
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.secondary
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberImagePainter(data = currentSong.image),
                contentDescription = currentSong.nameSong,
                modifier = Modifier
                    .size(300.dp)
                    .padding(top = 10.dp, bottom = 10.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = currentSong.nameSong,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .size(100.dp)
                        .weight(1f)
                )

                OutlinedButton(onClick = { /* Acción del botón */ }) {
                    Image(
                        painter = painterResource(R.drawable.favorite),
                        contentDescription = "Favorite",
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }

            Text(
                text = currentSong.nameArtist,
                style = Typography.bodyLarge,
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.Start)
            )

            Slider(
                value = progress,
                onValueChange = { newProgress ->
                    progress = newProgress
                },
                valueRange = 0f..totalDuration,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(100.dp)
            ) {
                OutlinedButton(onClick = {
                    if (currentSongIndex > 0) currentSongIndex--
                }) {
                    Image(
                        painter = painterResource(R.drawable.previous),
                        contentDescription = "Previous",
                        modifier = Modifier
                            .size(50.dp)
                            .padding(10.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
                OutlinedButton(onClick = { isPlaying = !isPlaying }) {
                    Image(
                        painter = painterResource(if (isPlaying) R.drawable.pause else R.drawable.play),
                        contentDescription = if (isPlaying) "Pause" else "Play",
                        modifier = Modifier
                            .size(50.dp)
                            .padding(10.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
                OutlinedButton(onClick = {
                    if (currentSongIndex < songs.size - 1) currentSongIndex++
                }) {
                    Image(
                        painter = painterResource(R.drawable.next),
                        contentDescription = "Next",
                        modifier = Modifier
                            .size(50.dp)
                            .padding(10.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
        }
    }
}
