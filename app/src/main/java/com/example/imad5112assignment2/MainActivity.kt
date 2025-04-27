package com.example.imad5112assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button // For the start button
import android.content.Intent // To navigate to the quiz screen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton = findViewById<Button>(R.id.startButton)
        startButton.setOnClickListener {
            startActivity(Intent(this, QuizActivity::class.java))
        }
    }
}