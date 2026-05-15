package com.notesappify.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "searches")
data class Search(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val param: String = "",
    val registerTime: Long = System.currentTimeMillis()
)