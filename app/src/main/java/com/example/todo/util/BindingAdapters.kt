package com.example.todo.util

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.todo.R
import com.example.todo.database.Todo
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("setCompletedTaskOrNot")
fun ImageView.setCompletedTaskOrNot(task:Todo){
    if (!task.isDone){
        visibility = View.VISIBLE
        background=ContextCompat.getDrawable(context, R.color.blue)
        setImageResource(R.drawable.ic_check)
    }else{
       visibility = View.GONE
    }
}
@BindingAdapter("setCompletedTextOrNot")
fun TextView.setCompletedTaskOrNot(task:Todo){
    if (task.isDone){
        visibility = View.VISIBLE
        text= "Done!"

    }else{
        visibility = View.GONE
    }
}
@SuppressLint("SimpleDateFormat")
@BindingAdapter("formatDate")
fun Button.formatDate(date: Date){
    val format = SimpleDateFormat("dd/MM/yyyy")
    text = format.format(date)
}