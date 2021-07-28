package com.example.simple_todo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.simple_todo.modal.Word

@Dao
interface DaoWord {
    @Insert
    fun insert(word: Word)

    @Query("SELECT * FROM word_table")
    fun getAllWords(): LiveData<List<Word>>

    @Query("DELETE FROM word_table WHERE word = :title")
    fun deleteWord(title: String)

}