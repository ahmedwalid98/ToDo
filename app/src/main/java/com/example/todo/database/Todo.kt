package com.example.todo.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
@kotlinx.parcelize.Parcelize
data class Todo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var id:Int?=null,
    @ColumnInfo
    var title:String,
    @ColumnInfo
    var description: String,
    @ColumnInfo
    var date: Date,
    @ColumnInfo
    var isDone:Boolean
):Parcelable