package com.example.quizes

data class Question (
    val id:Int,
    val ques:String,
    val image: Int,
    val option1:String,
    val option2:String,val option3:String,val option4:String,val correct_option:Int
)