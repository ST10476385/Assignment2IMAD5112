package com.example.imad5112assignment2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ReviewActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        // Get data from ScoreActivity
        val questions = intent.getStringArrayListExtra("questions") ?: arrayListOf()
        val userAnswers = intent.getStringArrayListExtra("userAnswers") ?: arrayListOf()
        val correctAnswers = intent.getStringArrayListExtra("correctAnswers") ?: arrayListOf()

        // Connect to layout views
        val reviewContainer = findViewById<LinearLayout>(R.id.reviewContainer) // Make sure this is correct in XML
        val reviewAgainButton = findViewById<Button>(R.id.btnReviewAgain)
        val exitAppButton = findViewById<Button>(R.id.btnExitApp)

        // Add all reviews to the layout
        for (i in questions.indices) {
            val question = questions[i]
            val userAnswer = userAnswers.getOrNull(i) ?: "No Answer"
            val correctAnswer = correctAnswers.getOrNull(i) ?: "No Answer"

            val reviewText = TextView(this).apply {
                text = "Q${i + 1}: $question\nYour Answer: $userAnswer\nCorrect Answer: $correctAnswer"
                textSize = 16f
                setPadding(16, 16, 16, 16)
            }
// Set the text color to white
            reviewText.setTextColor(resources.getColor(android.R.color.white)) // Set text color to white

            reviewContainer.addView(reviewText)
        }

        // Restart the quiz
        reviewAgainButton.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Exit the app
        exitAppButton.setOnClickListener {
            finishAffinity()
        }
    }
}