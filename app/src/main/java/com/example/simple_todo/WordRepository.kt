package com.example.simple_todo

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.simple_todo.modal.Word

class WordRepository(application: Application) {
    var wordDb: WordRoomDB = WordRoomDB.getDatabase(application.applicationContext)
    var daoWord: DaoWord = wordDb.daoWord()
    var mAllWords = daoWord.getAllWords()

    fun getAllWords(): LiveData<List<Word>> {
        return mAllWords
    }

    fun insertWord(word: Word) {
        var insertTask = InsertTask(daoWord,word)
        insertTask.execute()
    }

    fun deleteWord(id: Int) {
        var deleteTask = DeleteTask(daoWord,id)
        deleteTask.execute()
    }

}