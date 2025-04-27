package com.example.imad5112assignment2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {

    private val questions = arrayOf(
        "Julius Caesar was assassinated on the Ides of March.",
        "The Colosseum could seat up to 10,000 people.",
        "Romans used concrete to build many of their structures.",
        "The Roman Empire fell in 476 AD.",
        "Gladiators always fought to the death."
    )

    private val answers = arrayOf(true, true, false, true, false)

    private var currentQuestionIndex = 0
    private var score = 0

    private val userAnswers = arrayListOf<String>()
    private val correctAnswersList = arrayListOf<String>()

    private val TAG = "QuizActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val questionTextView = findViewById<TextView>(R.id.questionTextView)
        val feedbackTextView = findViewById<TextView>(R.id.feedbackTextView)
        val trueButton = findViewById<Button>(R.id.trueButton)
        val falseButton = findViewById<Button>(R.id.falseButton)
        val nextButton = findViewById<Button>(R.id.nextButton)

        questionTextView.text = questions[currentQuestionIndex]

        trueButton.setOnClickListener {
            checkAnswer(true, feedbackTextView)
        }

        falseButton.setOnClickListener {
            checkAnswer(false, feedbackTextView)
        }

        nextButton.setOnClickListener {
            currentQuestionIndex++

            if (currentQuestionIndex < questions.size) {
                questionTextView.text = questions[currentQuestionIndex]
                feedbackTextView.text = ""
            } else {
                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("SCORE", score)
                intent.putStringArrayListExtra("questions", ArrayList(questions.toList()))
                intent.putStringArrayListExtra("userAnswers", userAnswers)
                intent.putStringArrayListExtra("correctAnswers", correctAnswersList)
                startActivity(intent)
                finish()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun checkAnswer(userAnswer: Boolean, feedbackTextView: TextView) {
        val correctAnswer = answers[currentQuestionIndex]

        userAnswers.add(userAnswer.toString())
        correctAnswersList.add(correctAnswer.toString())

        if (userAnswer == correctAnswer) {
            feedbackTextView.text = "Well done, young senator! You chose wisely!"
            score++
            Log.d(TAG, "Triumph! Correct. Score: $score")
        } else {
            feedbackTextView.text = "Oh no! The gods are not pleased."
            Log.d(TAG, "Defeat... Incorrect. Score: $score")
        }
    }
}
