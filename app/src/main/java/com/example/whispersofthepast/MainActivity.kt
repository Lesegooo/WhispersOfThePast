package com.example.whispersofthepast

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whispersofthepast.ui.theme.WhispersOfThePastTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            )

            {
                Text(
                    modifier = Modifier.padding(16.dp) ,
                    text = "Whispers Of The Past",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(text = "Welcome to Whispers Of The Past",
                    fontStyle = FontStyle.Italic,
                    fontSize = 25.sp
                )
                Text(
                    modifier = Modifier.padding(30.dp),
                    text = "This application present historical questions," +
                            "loop through each flash card and" +
                            "provide a score based on the user's answers." +
                            "Press Start to begin.",
                    fontSize = 18.sp
                )
                Button(onClick = {
                    //Runs when button is clicked
                    val start = Intent(this@MainActivity,FlashCards::class.java)
                    startActivity(start)
                })
                {
                    Text(text = "Start",
                        fontSize = 20.sp)
                }
            }

        }
    }
}
