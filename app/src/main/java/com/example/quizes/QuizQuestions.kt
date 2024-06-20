package com.example.quizes

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuizQuestions : AppCompatActivity(), View.OnClickListener {
    private var currentPosition:Int = 1
    private var questionslist: ArrayList<Question>? = null
    private var selectedPosition: Int = 0
    private var count: Int = 0
    private var mUserName: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz_questions)
        mUserName = intent.getStringExtra(Constansts.NAME)
        questionslist = Constansts.getQuestions()

        setquestion()
        findViewById<TextView>(R.id.optoin_one).setOnClickListener(this)
        findViewById<TextView>(R.id.optoin_two).setOnClickListener(this)
        findViewById<TextView>(R.id.optoin_three).setOnClickListener(this)
        findViewById<TextView>(R.id.optoin_four).setOnClickListener(this)
        findViewById<Button>(R.id.submit).setOnClickListener(this)


    }
    @SuppressLint("SetTextI18n")
    private fun setquestion() {
        defaultQuestionsView()
        if(currentPosition == questionslist!!.size) {
            findViewById<Button>(R.id.submit).text = "FINISH"
        } else {
            findViewById<Button>(R.id.submit).text = "SUBMIT"
        }
        val ques = questionslist!![currentPosition - 1]
        findViewById<TextView>(R.id.ques_id).text = "Question: ${currentPosition}"
        findViewById<TextView>(R.id.ques_text).text = ques.ques
        findViewById<ProgressBar>(R.id.progress_bar).progress = currentPosition
        findViewById<TextView>(R.id.progress).text =
            "$currentPosition" + "/" + "10"
        findViewById<TextView>(R.id.optoin_one).text = ques.option1
        findViewById<TextView>(R.id.optoin_two).text = ques.option2
        findViewById<TextView>(R.id.optoin_three).text = ques.option3
        findViewById<TextView>(R.id.optoin_four).text = ques.option4
        findViewById<ImageView>(R.id.flag_image).setImageResource(ques.image)
    }
    private fun defaultQuestionsView() {
        val option = ArrayList<TextView>()
        option.add(0, findViewById(R.id.optoin_one))
        option.add(1,findViewById(R.id.optoin_two))
        option.add(2, findViewById(R.id.optoin_three))
        option.add(3, findViewById(R.id.optoin_four))
        for(opt in option) {
            opt.setTextColor(Color.parseColor("#FF000000"))
            opt.typeface = Typeface.DEFAULT
            opt.background = ContextCompat.getDrawable(this,
                R.drawable.default_optoin_bar)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.optoin_one  -> {
                selectedQuestionView(findViewById(R.id.optoin_one) , 1)
            }
            R.id.optoin_two -> {
                selectedQuestionView(findViewById(R.id.optoin_two), 2)
            }
            R.id.optoin_three -> {
                selectedQuestionView(findViewById(R.id.optoin_three), 2)
            }
            R.id.optoin_four -> {
                selectedQuestionView(findViewById(R.id.optoin_four), 2)
            }
            R.id.submit -> {
                if(selectedPosition == 0){
                    currentPosition++
                    if(currentPosition <= questionslist!!.size){
                        setquestion()
                    }else {
                        Toast.makeText(this, "Your correct answers are ${count}", Toast.LENGTH_SHORT ).show()
                        val intent = Intent(this, ResultActivity::class.java)
                        intent.putExtra(Constansts.NAME, mUserName)
                        intent.putExtra(Constansts.TOTAL_ANS, questionslist!!.size)
                        intent.putExtra(Constansts.CORRECT_QUE, count)
                        startActivity(intent)
                        finish()
                    }
                }
                else {
                    val ques = questionslist?.get(currentPosition - 1)
                    if(ques!!.correct_option != selectedPosition) {
                        answerView(selectedPosition, R.drawable.wrong_optoin_bar)
                    } else {
                        count++
                    }
                    answerView(ques.correct_option, R.drawable.correct_optoin_bar)
                    if(currentPosition == questionslist!!.size) {
                        findViewById<Button>(R.id.submit).text = "FINISH"
                    }else {
                        findViewById<Button>(R.id.submit).text = "Go to the Next Question"
                    }
                    selectedPosition = 0
                }
            }
        }
    }
    private fun selectedQuestionView(tv: TextView, selectednum: Int) {
        defaultQuestionsView()
        selectedPosition = selectednum
        tv.setTextColor(Color.parseColor("#FF000000"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.select_optoin_bar)

    }
    private  fun answerView(answer: Int, drawableView: Int)  {
        when(answer) {
            1 -> {
                findViewById<TextView>(R.id.optoin_one).background = ContextCompat.getDrawable(this, drawableView)
            }
            2 -> {
                findViewById<TextView>(R.id.optoin_two).background = ContextCompat.getDrawable(this, drawableView)
            }
            3 -> {
                findViewById<TextView>(R.id.optoin_three).background = ContextCompat.getDrawable(this, drawableView)
            }
            4 -> {
                findViewById<TextView>(R.id.optoin_four).background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }
}