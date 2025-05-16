package com.example.whispersofthepast

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whispersofthepast.ui.theme.WhispersOfThePastTheme
import org.intellij.lang.annotations.JdkConstants.FontStyle
import kotlin.reflect.KProperty

class FlashCards : ComponentActivity() {
    private fun Text(
        text: String,
        horizontalAlignment: Alignment.Horizontal,
        verticalArrangement: Any,
        sp: TextUnit,
        fontWeight: FontWeight,
    ) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Initialize questions
            val questions = listOf(
                Question("Leonardo da Vinci painted the Starry Night.", false,),
                Question(
                    "The Boston Tea Party took Place in 1773 as a protest against British taxation.",
                    true
                ),
                Question(
                    "Nelson Mandela was the first black President of South Africa after apartheid ended.",
                    true
                ),
                Question("The Enlightenment was a cultural movement in 1453.", true),
                Question("The Treaty of Versailles was signed at the end of World War II.", false)
            )

            var currentIndex by remember { mutableStateOf(0) }
            var score by remember { mutableStateOf(0) }
            var feedback by remember { mutableStateOf("") }
            var quizComplete by remember { mutableStateOf(false) }
            val userAnswers =
                remember { mutableStateListOf<Boolean?>(null, null, null, null, null) }

            // Check if the quiz is complete
            if (currentIndex >= questions.size) {
                quizComplete = true
            }

            Column(
                modifier = Modifier.padding(18.dp),horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (quizComplete as Boolean) {
                    // Display the score and feedback
                    Text(

                        text = "Quiz Complete! Your score is: $score/${questions.size}",)


                    val feedbackMessage = if (score >= 3) {
                        "Great job!"
                    } else {
                        "Keep practicing!"
                    }
                    Text(text = feedbackMessage,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold)

                    // Review button to show results
                    Button(onClick = {
                        // Create a Boolean array for user answers, replacing nulls with false
                        val answersArray = userAnswers.map { it ?: false }.toBooleanArray()

                        val intent = Intent(this@FlashCards, ResultsActivity::class.java).apply {
                            putExtra("userAnswers", answersArray)
                            putExtra("questions", ArrayList(questions))
                            putExtra("score", score)
                        }
                        startActivity(intent)
                    }) {
                        Text(text = "Review")
                    }
                } else {
                    // Show the current question
                    val currentQuestion = questions[currentIndex]
                    Text(text = currentQuestion.text,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold)

                    Row(
                        modifier = Modifier.padding(top = 16.dp).fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(onClick = {
                            userAnswers[currentIndex as Int] = true // Store user's answer
                            if (currentQuestion.answer) score++
                            feedback = if (currentQuestion.answer) "Correct" else "Incorrect"
                        }) {
                            Text(text = "True")
                        }
                        Button(onClick = {
                            userAnswers[currentIndex as Int] = false // Store user's answer
                            if (!currentQuestion.answer) score++
                            feedback = if (!currentQuestion.answer) "Correct" else "Incorrect"
                        }) {
                            Text(text = "False")
                        }
                    }

                    Text(text = feedback, modifier = Modifier.padding(top = 16.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold)

                    Button(onClick = {
                        // Move to the next question
                        currentIndex++
                        feedback = ""
                    }, modifier = Modifier.padding(top = 16.dp)) {
                        Text("Next")
                    }
                }
            }
        }
    }

    private fun Text(
        text: String,
        horizontalAlignment: Alignment.Horizontal,
        verticalArrangement: Arrangement.HorizontalOrVertical,
        fontSize: TextUnit,
        fontWeight: FontWeight,
    ) {

    }
}

