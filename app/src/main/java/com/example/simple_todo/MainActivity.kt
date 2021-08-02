package com.example.simple_todo

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simple_todo.modal.Word
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.messaging.FirebaseMessaging

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
        FirebaseMessaging.getInstance().isAutoInitEnabled = true
        worddb = WordRoomDB.getDatabase(this)
        daoWord = worddb.daoWord()
        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)
        mWordViewModel.mAllWords.observe(this, Observer {
            (rv_main.adapter as rv_main_adapter).data = it
            (rv_main.adapter as rv_main_adapter).notifyDataSetChanged()
        })
        fab_rv = findViewById(R.id.fab_rv_main)
        fab_rv.setOnClickListener {
            var intent = Intent(this,NewWordActivity::class.java)
            startActivityForResult(intent,1)
        }
        rv_main = findViewById(R.id.rv_main)
        rv_main.layoutManager = LinearLayoutManager(this)
        rv_main.adapter = rv_main_adapter(words,mWordViewModel)
    }

    override fun onStart() {
        super.onStart()
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new FCM registration token
                val token: String = task.getResult().toString()

                // Log and toast
                //val msg = getString(R.string.msg_token_fmt, token)
                Log.d(TAG, token)
                Toast.makeText(this, token, Toast.LENGTH_SHORT).show()
            })
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
            val new_word = data?.getStringExtra("word").toString()
            Log.i("word",new_word)
            val word = Word()
            word.word = new_word
            mWordViewModel.insertWord(word)
        }
    }
}