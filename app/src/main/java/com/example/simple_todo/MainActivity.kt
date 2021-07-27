package com.example.simple_todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var rv_main: RecyclerView
    lateinit var fab_rv: FloatingActionButton
    var words = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fab_rv = findViewById(R.id.fab_rv_main)
        fab_rv.setOnClickListener {
            var intent = Intent(this,NewWordActivity::class.java)
            startActivityForResult(intent,1)
        }
        rv_main = findViewById(R.id.rv_main)
        rv_main.layoutManager = LinearLayoutManager(this)
        rv_main.adapter = rv_main_adapter(words)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==1) {
            words.add(data?.getStringExtra("word").toString())
            rv_main.adapter?.notifyDataSetChanged()
        }
    }
}