package com.example.soundscape.view
import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.transition.Slide
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.soundscape.R
import com.example.soundscape.ui.theme.SoundScapeTheme
import com.example.soundscape.ui.theme.Typography

class Reproductor : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContent {
                SoundScapeTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize()
                    ){
                        Reproductor(name = "rels b", nameSong = "Sin mirar las señales")
                    }
            }
    }
}
}
@Composable
fun imageandname(name: String, modifier: Modifier = Modifier, image: Uri){
    val context = LocalContext.current
    val activity = context as? Activity


}

@Composable
fun Reproductor(name: String, nameSong: String, modifier: Modifier = Modifier){
    val context = LocalContext.current
    val activity = context as? Activity

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
        ){Column {


        Image(
            painter = painterResource(R.drawable.applogo),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .size(500.dp)
                .padding(top = 10.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ){
            Text(
                text = "$nameSong",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .size(100.dp)
                    .weight(1f)
        )

            OutlinedButton(onClick = {
            }) {
                Image(
                    painter = painterResource(R.drawable.favorite),
                    contentDescription = "Contact profile picture",
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.CenterVertically)
                )
            }
        }


        Text(
            text = " $name",
            style = Typography.bodyLarge,
            modifier = modifier
                .padding(10.dp)
                .align(Alignment.Start)
        )
            Slider(
                value = 0.5f,
                onValueChange = {},
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(100.dp)
            ){
                OutlinedButton(onClick = { }){
                    Image(
                        painter= painterResource(R.drawable.previous),
                        contentDescription = "Contact profile picture",
                        modifier = Modifier
                            .size(50.dp)
                            .padding(10.dp)
                            .align(Alignment.CenterVertically)
                            .border(0.dp, MaterialTheme.colorScheme.tertiary)
                    )

                }
                OutlinedButton(onClick = {}){
                    Image(
                        painter= painterResource(R.drawable.play),
                        contentDescription = "Contact profile picture",
                        modifier = Modifier
                            .size(50.dp)
                            .padding(10.dp)
                            .align(Alignment.CenterVertically)
                    )
                }


                OutlinedButton(onClick = { }){
                    Image(
                        painter= painterResource(R.drawable.next),
                        contentDescription = "Contact profile picture",
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

