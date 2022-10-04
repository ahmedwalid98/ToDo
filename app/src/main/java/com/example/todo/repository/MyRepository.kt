package com.example.todo.repository

import com.example.todo.database.Todo
import com.example.todo.database.TodoDAO
import com.example.todo.database.TodoDatabase
import java.util.*

class MyRepository(private val todoDatabase: TodoDatabase) {
    val allTasks = todoDatabase.todoDao().getAllTasks()
    fun oneTask(todo: Todo) = todoDatabase.todoDao().getTaskById(todo.id!!)
    fun taskByDay(time:Date) = todoDatabase.todoDao().selectTasksByDate(time)
    suspend fun insertTask(todo: Todo){
        todoDatabase.todoDao().insert(todo)
    }

    suspend fun updateTask(todo: Todo){
        todoDatabase.todoDao().update(todo)
    }

    suspend fun deleteTask(todo: Todo){
        todoDatabase.todoDao().delete(todo)
    }
}