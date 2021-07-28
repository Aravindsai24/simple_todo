package com.example.simple_todo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.simple_todo.modal.Word

class WordViewModel(application: Application) : AndroidViewModel(application) {
    val wRepository = WordRepository(application)
    val mAllWords: LiveData<List<Word>> = wRepository.getAllWords()

    fun getAllWords(): LiveData<List<Word>> {
        return mAllWords
    }

    fun insertWord(word: Word) {
        wRepository.insertWord(word)
    }
}