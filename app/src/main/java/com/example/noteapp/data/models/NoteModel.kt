package com.example.noteapp.data.models

import android.icu.text.DateFormat
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noteModel")
data class NoteModel(
    val title: String,
    val description: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
