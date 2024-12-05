package com.example.soundscape

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.soundscape.ui.theme.SoundScapeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SoundScapeTheme {
                Surface() {
                    Conversation(SampleData.conversationSample)
                }
            }
        }
    }
}

data class Message(val author: String, val body: String)

/**
 * SampleData for Jetpack Compose Tutorial
 */
object SampleData {
    // Sample conversation data
    val conversationSample = listOf(
        Message(
            "kevin",
            "Test...Test...Test..."
        ),
        Message(
            "Kevin",
            """List of Android versions:
            |Android KitKat (API 19)
            |Android Lollipop (API 21)
            |Android Marshmallow (API 23)
            |Android Nougat (API 24)
            |Android Oreo (API 26)
            |Android Pie (API 28)
            |Android 10 (API 29)
            |Android 11 (API 30)
            |Android 12 (API 31)""".trim()
        ),
        Message(
            "Kevin",
            """I think Kotlin is my favorite programming language.
            |It's so much fun!""".trim()
        ),
        Message(
            "kevin",
            "Searching for alternatives to XML layouts..."
        ),
        Message(
            "kevin",
            """Hey, take a look at Jetpack Compose, it's great!
            |It's the Android's modern toolkit for building native UI.
            |It simplifies and accelerates UI development on Android.
            |Less code, powerful tools, and intuitive Kotlin APIs :)""".trim()
        ),
        Message(
            "kevin",
            "It's available from API 21+ :)"
        ),
        Message(
            "kevin",
            "Writing Kotlin for UI seems so natural, Compose where have you been all my life?"
        ),
        Message(
            "kevin",
            "Android Studio next version's name is Arctic Fox"
        ),
        Message(
            "kevin",
            "Android Studio Arctic Fox tooling for Compose is top notch ^_^"
        ),
        Message(
            "kevin",
            "I didn't know you can now run the emulator directly from Android Studio"
        ),
        Message(
            "kevin",
            "Compose Previews are great to check quickly how a composable layout looks like"
        ),
        Message(
            "kevin",
            "Previews are also interactive after enabling the experimental setting"
        ),
        Message(
            "kevin",
            "Have you tried writing build.gradle with KTS?"
        ),
    )
}


@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

@Composable
fun MessageCard(msg: Message) {
    Row(
        modifier = Modifier
            .padding(all = 8.dp)
            .statusBarsPadding()
    ) {
        Image(
            painter = painterResource(R.drawable.profilepicture),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.height(4.dp))

            Surface(shape = MaterialTheme.shapes.medium, shadowElevation = 5.dp) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewConversation() {
    SoundScapeTheme {
        Surface {
            Conversation(SampleData.conversationSample)
        }
    }
}


@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewMessageCard() {
    SoundScapeTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            MessageCard(Message("Kevin", "Jetpack Compose"))
        }
    }
}

