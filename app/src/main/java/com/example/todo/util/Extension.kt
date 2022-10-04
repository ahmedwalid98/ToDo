package com.example.todo.util

import java.util.*

fun Calendar.clearAll(){
    clear(Calendar.HOUR)
    clear(Calendar.SECOND)
    clear(Calendar.MINUTE)
    clear(Calendar.MILLISECOND)
}