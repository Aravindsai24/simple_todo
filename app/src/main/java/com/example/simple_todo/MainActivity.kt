package com.example.simple_todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simple_todo.modal.Word
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var rv_main: RecyclerView
    lateinit var fab_rv: FloatingActionButton
    lateinit var worddb : WordRoomDB
    lateinit var daoWord: DaoWord
    var words = ArrayList<Word>()
    lateinit var mWordViewModel: WordViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        worddb = WordRoomDB.getDatabase(this)
        daoWord = worddb.daoWord()
        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)
        mWordViewModel.mAllWords.observe(this, Observer {
            (rv_main.adapter as rv_main_adapter).data = it
        })

        fab_rv = findViewById(R.id.fab_rv_main)
        fab_rv.setOnClickListener {
            var intent = Intent(this,NewWordActivity::class.java)
            startActivityForResult(intent,1)
        }
        rv_main = findViewById(R.id.rv_main)
        rv_main.layoutManager = LinearLayoutManager(this)
        rv_main.adapter = rv_main_adapter(words)
    }

    override fun onResume() {
        super.onResume()
        //getDataAsynchronously()
    }

    private fun getDataAsynchronously() {
        var retrieveTask = RetrieveTask(this,daoWord,rv_main)
        retrieveTask.execute()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==1) {
            val word = data?.getStringExtra("word").toString()
            Log.i("word",word)
            insertWordAsynchronously(word)
        }
    }

    private fun insertWordAsynchronously(new_word: String) {
        val word = Word()
        word.word = new_word
        var insertTask = InsertTask(daoWord,word)
        insertTask.execute()
    }
}