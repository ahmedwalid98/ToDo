package com.example.todo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.database.Todo
import com.example.todo.repository.MyRepository
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel(private val repository: MyRepository):ViewModel() {
    val allTasks = repository.allTasks
    fun oneTask(todo: Todo) = repository.oneTask(todo)
    fun taskByDay(time:Date) = repository.taskByDay(time)
    fun insertTask(todo: Todo){
        viewModelScope.launch {
            repository.insertTask(todo)
        }
    }

    fun deleteTask(todo: Todo){
        viewModelScope.launch {
            repository.deleteTask(todo)
        }
    }

    fun updateTask(todo: Todo){
        viewModelScope.launch {
            repository.updateTask(todo)
        }
    }
}