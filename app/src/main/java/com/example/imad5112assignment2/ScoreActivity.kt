package com.example.imad5112assignment2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val score = intent.getIntExtra("SCORE", 0)
        val questions = intent.getStringArrayListExtra("questions") ?: ArrayList()
        val userAnswers = intent.getStringArrayListExtra("userAnswers") ?: ArrayList()
        val correctAnswers = intent.getStringArrayListExtra("correctAnswers") ?: ArrayList()

        val scoreTextView = findViewById<TextView>(R.id.scoreTextView)
        val feedbackTextView = findViewById<TextView>(R.id.scoreFeedbackTextView)
        val quoteTextView = findViewById<TextView>(R.id.romanQuoteTextView)
        val reviewButton = findViewById<Button>(R.id.reviewButton)
        val exitButton = findViewById<Button>(R.id.exitButton)

        scoreTextView.text = "Your Score: $score / ${questions.size}"

        feedbackTextView.text = when (score) {
            questions.size -> "You are a true Roman scholar! Veni, vidi, vici!"
            in 3..4 -> "Well fought, citizen. The Senate applauds your wisdom."
            else -> "The gods demand more study. Return stronger!"
        }

        quoteTextView.text = "Rome wasn’t built in a day... but neither is knowledge. – Proverb"

        reviewButton.setOnClickListener {
            val intent = Intent(this, ReviewActivity::class.java)
            intent.putStringArrayListExtra("questions", questions)
            intent.putStringArrayListExtra("userAnswers", userAnswers)
            intent.putStringArrayListExtra("correctAnswers", correctAnswers)
            startActivity(intent)
            finish()
        }

        exitButton.setOnClickListener {
            finishAffinity()
        }
    }
}
