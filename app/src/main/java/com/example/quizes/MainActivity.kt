package com.example.quizes

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val name = findViewById<TextView>(R.id.name_input)
        findViewById<Button>(R.id.Start_button).setOnClickListener {
            if(name.text.toString().isEmpty()) {
                Toast.makeText( this, "Please Enter your name first", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText( this, "Welcome ${name.text}", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, QuizQuestions::class.java)
                intent.putExtra(Constansts.NAME, findViewById<TextView>(R.id.name_input).text )
                startActivity(intent)
                finish()
            }
        }
    }
}