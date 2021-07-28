package com.example.simple_todo

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.simple_todo.modal.Word

class RetrieveTask(var context: Context,val daoWord: DaoWord, val rv_main: RecyclerView): AsyncTask<Void, Void, LiveData<List<Word>>>() {

    override fun doInBackground(vararg p0: Void?): LiveData<List<Word>> {
        return daoWord.getAllWords()
    }

    override fun onPostExecute(result: LiveData<List<Word>>?) {
        super.onPostExecute(result)
        /*
        if (result != null) {
            (rv_main.adapter as rv_main_adapter).data = result as List<Word>
            (rv_main.adapter as rv_main_adapter).notifyDataSetChanged()
        }
        */
    }

}