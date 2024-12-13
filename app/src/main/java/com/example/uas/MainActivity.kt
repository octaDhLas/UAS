package com.example.uas

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import android.content.Intent

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameInput = findViewById<EditText>(R.id.nameInput)
        val startButton = findViewById<Button>(R.id.startButton)
        val logoImage = findViewById<ImageView>(R.id.logoImage)  // Referensi ImageView jika ingin manipulasi lebih lanjut

        startButton.setOnClickListener {
            val name = nameInput.text.toString()
            if (name.isNotEmpty()) {
                val intent = Intent(this, QuizActivity::class.java)
                intent.putExtra("USER_NAME", name)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Silakan masukkan namamu", Toast.LENGTH_SHORT).show()
            }
        }
    }
}