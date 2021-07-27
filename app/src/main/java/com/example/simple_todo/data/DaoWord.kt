package com.example.simple_todo.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.simple_todo.modal.Word

@Dao
interface DaoWord {
    @Insert
    fun insert(word: Word)

    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAlphabetizedWords(): List<Word>

}