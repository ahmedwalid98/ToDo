package com.example.todo.database

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

@Dao
interface TodoDAO {
    @Insert
    suspend fun insert(todo: Todo)
    @Delete
    suspend fun delete(todo: Todo)
    @Update
    suspend fun update(todo: Todo)
    @Query("SELECT * FROM Todo")
    fun getAllTasks(): LiveData<List<Todo>>
    @Query("select * from Todo where date = :dateParam")
    fun selectTasksByDate(dateParam:Date):LiveData<List<Todo>>
    @Query("select * from Todo where id = :id")
    fun getTaskById(id:Int):LiveData<Todo>
}