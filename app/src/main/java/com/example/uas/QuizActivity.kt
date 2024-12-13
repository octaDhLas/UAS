package com.example.uas

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class QuizActivity : Activity() {
    // Daftar soal, opsi jawaban, dan jawaban yang benar
    private val questions = listOf(
        Question(
            "Apa ibu kota Korea Selatan?",
            listOf("Paris", "London", "Busan", "Seoul"),
            "Seoul"
        ),
        Question(
            "Siapa Presiden Ke-7 Indonesia?",
            listOf("Soeharto", "Habibie", "Joko Widodo", "Megawati"),
            "Joko Widodo"
        ),
        Question(
            "Berapa banyak provinsi di Indonesia?",
            listOf("38", "37", "34", "36"),
            "38"
        ),
        Question(
            "Di mana Candi Borobudur berada?",
            listOf("Yogyakarta", "Magelang", "Bali", "Surabaya"),
            "Magelang"
        ),
        Question(
            "Siapa paling ganteng di NCT?",
            listOf("Jaemin", "Haechan", "Jaehyun", "Taeyong"),
            "Haechan"
        )
    )

    private var currentQuestionIndex = 0
    private var score = 0
    private var userName: String? = null

    // Data class untuk soal dan jawaban
    data class Question(
        val questionText: String,
        val options: List<String>,
        val correctAnswer: String
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        userName = intent.getStringExtra("USER_NAME")

        val questionText = findViewById<TextView>(R.id.questionText)
        val optionsGroup = findViewById<RadioGroup>(R.id.optionsGroup)
        val nextButton = findViewById<Button>(R.id.nextButton)

        loadQuestion(questionText, optionsGroup)

        nextButton.setOnClickListener {
            val selectedOptionId = optionsGroup.checkedRadioButtonId
            if (selectedOptionId != -1) {
                val selectedOption = findViewById<RadioButton>(selectedOptionId)
                // Memeriksa apakah jawaban yang dipilih benar
                if (selectedOption.text == questions[currentQuestionIndex].correctAnswer) {
                    score++
                }
                currentQuestionIndex++
                if (currentQuestionIndex < questions.size) {
                    loadQuestion(questionText, optionsGroup)
                } else {
                    val intent = Intent(this, ResultActivity::class.java)
                    intent.putExtra("USER_NAME", userName)
                    intent.putExtra("SCORE", score)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun loadQuestion(questionText: TextView, optionsGroup: RadioGroup) {
        // Memuat soal dan jawaban
        val currentQuestion = questions[currentQuestionIndex]
        questionText.text = currentQuestion.questionText
        optionsGroup.clearCheck()
        optionsGroup.removeAllViews()

        // Mengacak urutan pilihan jawaban
        val options = currentQuestion.options.shuffled()
        for (option in options) {
            val radioButton = RadioButton(this)
            radioButton.text = option
            radioButton.textSize = 23f
            radioButton.setTextColor(resources.getColor(android.R.color.black))
            optionsGroup.addView(radioButton)
        }
    }
}
