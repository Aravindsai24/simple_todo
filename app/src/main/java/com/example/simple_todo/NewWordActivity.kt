package com.example.simple_todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class NewWordActivity : AppCompatActivity() {
    lateinit var new_word_text: EditText
    lateinit var btn_save: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)
        new_word_text = findViewById(R.id.new_word_text)
        btn_save = findViewById(R.id.btn_new_word_save)
        btn_save.setOnClickListener {
            var intent = Intent()
            intent.putExtra("word",new_word_text.text.toString())
            setResult(RESULT_OK,intent)
            finish()
        }
    }
}