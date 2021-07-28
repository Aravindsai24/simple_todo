package com.example.simple_todo

import android.content.Context
import android.os.AsyncTask
import androidx.recyclerview.widget.RecyclerView
import com.example.simple_todo.modal.Word

class RetrieveTask(var context: Context,val daoWord: DaoWord, val rv_main: RecyclerView): AsyncTask<Void, Void, List<Word>>() {

    override fun doInBackground(vararg p0: Void?): List<Word> {
        return daoWord.getAlphabetizedWords()
    }

    override fun onPostExecute(result: List<Word>?) {
        super.onPostExecute(result)
        if (result != null) {
            (rv_main.adapter as rv_main_adapter).data = result
            (rv_main.adapter as rv_main_adapter).notifyDataSetChanged()
        }
    }

}