package com.example.simple_todo

import android.os.AsyncTask
import com.example.simple_todo.modal.Word

class InsertTask(val daoWord: DaoWord, val word: Word): AsyncTask<Void, Void, Void>() {
    override fun doInBackground(vararg p0: Void?): Void? {
        daoWord.insert(word)
        return null
    }
}
