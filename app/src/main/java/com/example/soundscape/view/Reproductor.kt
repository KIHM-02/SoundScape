package com.example.soundscape.view
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import com.example.soundscape.R
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soundscape.ui.theme.SoundScapeTheme
import com.example.soundscape.ui.theme.Typography
import java.time.format.TextStyle

class Reproductor : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
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
            )
        }
    }
}


@Composable
fun Reproductor(name: String, nameSong: String, modifier: Modifier = Modifier){
    val fontSize = if (nameSong.length <= 10)
    { 24.sp }
    else if (nameSong.length <= 20) { 20.sp }
    else { 16.sp }
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
                .padding(10.dp)
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
                        .padding(10.dp)
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
            LinearProgressIndicator(
                progress = 0.5f,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .align(Alignment.CenterHorizontally)
                    .size(10.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()

                    .size(100.dp)
                    .padding(10.dp)
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
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SoundScapeTheme {
        Reproductor(name = "rels b", nameSong = "Sin mirar las señales")
    }
}
