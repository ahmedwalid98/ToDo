package com.example.todo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Todo::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class TodoDatabase: RoomDatabase() {
    abstract fun todoDao() :TodoDAO
    companion object{
        @Volatile
        private var instance: TodoDatabase? = null

        fun getInstance(context: Context):TodoDatabase =
            instance ?: synchronized(this){
                instance ?: getDatabase(context).also { instance = it }
            }

        private fun getDatabase(context: Context):TodoDatabase {
            return Room.databaseBuilder(
                context,
                TodoDatabase::class.java,
                "TodoDatabase"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }


}