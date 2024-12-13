package com.example.uas

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import android.widget.Button

class ResultActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val userName = intent.getStringExtra("USER_NAME")
        val score = intent.getIntExtra("SCORE", 0)

        val resultText = findViewById<TextView>(R.id.resultText)
        val finishButton = findViewById<Button>(R.id.finishButton)

        resultText.text = "Selamat, $userName! Score kamu adalah $score."

        finishButton.setOnClickListener {
            finish()
        }
    }
}
