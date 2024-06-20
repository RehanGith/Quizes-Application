package com.example.quizes

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        findViewById<TextView>(R.id.user_name).text = intent.getStringExtra(Constansts.NAME)
        val CA:Int  = intent.getIntExtra(Constansts.CORRECT_QUE, 0)
        val TA:Int = intent.getIntExtra(Constansts.TOTAL_ANS, 0)
        findViewById<TextView>(R.id.result_score).text = "Your correct answers are $CA. out of $TA"

        findViewById<Button>(R.id.finish_button).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}