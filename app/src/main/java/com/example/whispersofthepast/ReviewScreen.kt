package com.example.whispersofthepast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class ReviewScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Get data from intent
            val userAnswers = intent.getBooleanArrayExtra("userAnswers") ?: booleanArrayOf()
            val questions = intent.getParcelableArrayListExtra<Question>("questions") ?: arrayListOf()
            val score = intent.getIntExtra("score", 0)

            // Layout to display results
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                // Display score
                Text(text = "Your Score: $score/${questions.size}",
                )

                // Loop through questions and answers
                for (i in questions.indices) {
                    val question = questions[i]
                    val userAnswer = userAnswers.getOrNull(i) ?: false
                    val isCorrect = question.answer == userAnswer

                    // Display the question
                    Text(text = "Q${i + 1}: ${question.text}",modifier = Modifier.padding(18.dp))
                    Text(text = "Correct Answer: ${if (question.answer) "True" else "False"}")
                    Text(text = "Your Answer: ${if (userAnswer) "True" else "False"}")
                    Text(text = "Result: ${if (isCorrect) "Correct" else "Incorrect"}")
                    Spacer(modifier = Modifier.height(8.dp)) // Spacing between questions
                }
            }
        }
    }
}