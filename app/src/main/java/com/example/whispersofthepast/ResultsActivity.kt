package com.example.whispersofthepast

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whispersofthepast.ui.theme.WhispersOfThePastTheme

class ResultsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val userAnswers = intent.getBooleanArrayExtra("userAnswer") ?: booleanArrayOf()
            val questions = intent.getParcelableArrayListExtra<Question>("questions") ?: arrayListOf()
            val score = intent.getIntExtra("score",0)

            Column {
                Text(text = "Your Score: $score/${ questions.size}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(18.dp))

                //Loop through questions and answers
                for (i in questions.indices){
                    val question = questions [i]
                    val userAnswer = userAnswers.getOrNull(i) ?: false
                    val isCorrect = question.answer == userAnswer

                    //Output the questions
                    Text(text = "Q${i + 1}: ${question.text}",modifier = Modifier.padding(18.dp))
                    Text(text = "Correct Answer: ${if (question.answer as Boolean) "True" else "False"}")
                    Text(text = "Your Answer: ${if (userAnswer) "True" else "False"}")
                    Text(text = "Result: ${if (isCorrect) "True" else "False"}")
                    Spacer(modifier = Modifier.height(8.dp))
                }
                // Retry button to start the quiz
                Button(onClick = {
                    //Restart the FlashCard screen
                    val intent = Intent(this@ResultsActivity, FlashCards::class.java)
                    startActivity(intent)
                    finish()
                }) {

                    Text(text = "Try Again")
                }

                //Exit button to close the app
                Button(onClick =  {
                    finishAffinity()
                }) {
                    Text(text = "Exit")
                }


            }
        }
    }
}
