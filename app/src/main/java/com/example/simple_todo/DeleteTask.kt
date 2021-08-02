package com.example.simple_todo

import android.os.AsyncTask
import com.example.simple_todo.modal.Word

class DeleteTask(val daoWord: DaoWord, val id: Int): AsyncTask<Void, Void, Void>() {
    override fun doInBackground(vararg p0: Void?): Void? {
        daoWord.deleteWord(id)
        return null
    }
}