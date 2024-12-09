package com.example.soundscape.view

import android.content.Context
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soundscape.controller.DataMusic
import com.example.soundscape.model.Music
import com.example.soundscape.ui.theme.SoundScapeTheme
import com.example.soundscape.view.screens.backgroundGradient
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class AddMusic : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SoundScapeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AddSongScreen()
                        Spacer(modifier = Modifier.height(26.dp))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun AddSongScreen() {
    var songName by remember { mutableStateOf("") }
    var artistName by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf("Unknown") }
    var image by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }
    var filePath by remember { mutableStateOf("") }
    val uri = "android.resource://com.example.soundscape/raw/"
    val gradientColorList = listOf(
        Color(0xFF1F3D83), // Azul Marino
        Color(0xFF097CBF)  // Azul Cielo
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = backgroundGradient(
                isVerticalGradient = true,
                colors = gradientColorList
            ))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Agregar Canción",
            fontSize = 28.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = songName,
            onValueChange = { songName = it },
            label = { Text("Nombre de la canción") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = artistName,
            onValueChange = { artistName = it },
            label = { Text("Nombre del artista") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        )
        Spacer(modifier = Modifier.height(16.dp))
        menuDesplegable { selectedGenre ->
            genre = selectedGenre
        }
        Spacer(modifier = Modifier.height(16.dp))
        addImage { imagePath ->
            image = imagePath
        }
        Spacer(modifier = Modifier.height(16.dp))
        addMp3 { mp3Path ->
            filePath = mp3Path
            duration = getMp3Duration(filePath)
        }
        Spacer(modifier = Modifier.height(16.dp))
        saveData(songName, artistName, genre, image, duration, uri)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun menuDesplegable(
    onGenreSelected: (String) -> Unit
) {
    val musicGender = listOf("Unknown", "Rock", "Pop", "Reggae")
    var expandedState by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(musicGender[0]) }
    Column(modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Elige el género", fontSize = 28.sp, color = Color.White)
        Spacer(modifier = Modifier.height(5.dp))
        ExposedDropdownMenuBox(
            expanded = expandedState,
            onExpandedChange = { expandedState = !expandedState }
        ) {
            TextField(
                modifier = Modifier.menuAnchor(),
                value = selectedOption,
                onValueChange = {},
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedState) },
                readOnly = true,
                textStyle = TextStyle.Default.copy(fontSize = 28.sp)
            )
            ExposedDropdownMenu(
                expanded = expandedState,
                onDismissRequest = { expandedState = false }
            ) {
                musicGender.forEach { eachOption ->
                    DropdownMenuItem(
                        onClick = {
                            selectedOption = eachOption
                            expandedState = false
                            onGenreSelected(eachOption)
                        },
                        text = { Text(text = eachOption, fontSize = 28.sp) }
                    )
                }
            }
        }
    }
}

@Composable
fun addImage(onImageSelected: (String) -> Unit) {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        selectedImageUri = uri
    }

    Column {
        Button(onClick = { launcher.launch("image/*") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1CA94B))) {
            Text("Seleccionar Imagen", fontSize = 18.sp)
        }

        selectedImageUri?.let { uri ->
            val imageFileName = "image_${System.currentTimeMillis()}.jpg" // Unique filename
            val imageFilePath = context.filesDir.absolutePath + "/" + imageFileName

            try {
                val inputStream = context.contentResolver.openInputStream(uri)
                val outputStream = FileOutputStream(File(imageFilePath))

                inputStream?.use { input ->
                    outputStream.use { output ->
                        input.copyTo(output)
                    }
                }

                onImageSelected(imageFilePath) // Pass the internal file path
            } catch (e: IOException) {
                // Handle error
            }
        }
    }
}

@Composable
fun addMp3(onMp3Selected: (String) -> Unit) {
    var selectedMp3Uri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        selectedMp3Uri = uri
    }

    Column {
        Button(onClick = { launcher.launch("audio/*") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1CA94B))) {
            Text("Seleccionar Archivo MP3", fontSize = 18.sp)
        }

        selectedMp3Uri?.let { uri ->
            val mp3FileName = "audio_${System.currentTimeMillis()}.mp3" // Unique filename
            val mp3FilePath = context.filesDir.absolutePath + "/" + mp3FileName

            try {
                val inputStream = context.contentResolver.openInputStream(uri)
                val outputStream = FileOutputStream(File(mp3FilePath))

                inputStream?.use { input ->
                    outputStream.use { output ->
                        input.copyTo(output)
                    }
                }

                onMp3Selected(mp3FilePath) // Pass the internal file path
            } catch (e: IOException) {
                // Handle error
            }
        }
    }
}

@Composable
fun saveData(songName: String, artistName: String, genre: String, image: String, duration: String, uri: String) {
    var showDialog by remember { mutableStateOf(false) }

    Column {
        // Show dialog on click
        Button(onClick = { showDialog = true },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1CA94B)))
        {
            Text("Guardar", fontSize = 18.sp)
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Confirmar") },
                text = { Text("¿Estás seguro de que quieres guardar esta canción?") },
                confirmButton = {
                    Button(onClick = {
                        val music = Music(songName, artistName, image, duration, genre, uri)
                        DataMusic.addMusic(music)
                        Log.d("AddMusic", "Music added: $music")
                        showDialog = false // Dismiss dialog after saving
                    }) {
                        Text("Guardar")
                    }
                },
                dismissButton = {
                    Button(onClick = { showDialog = false }) { // Dismiss dialog
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}

fun getMp3Duration(filePath: String): String {
    val retriever = MediaMetadataRetriever()
    retriever.setDataSource(filePath)
    val durationStr = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
    val duration = durationStr?.toLong() ?: 0L
    val minutes = duration / 1000 / 60
    val seconds = (duration / 1000) % 60
    retriever.release()
    return String.format("%d:%02d", minutes, seconds)
}

private fun getRealPathFromURI(context: Context, contentUri: Uri): String? {
    val cursor = context.contentResolver.query(contentUri, null, null, null, null)
    return if (cursor == null) {
        contentUri.path
    } else {
        cursor.moveToFirst()
        val index = cursor.getColumnIndex("_data")
        cursor.getString(index).also {
            cursor.close()
        }
    }
}