package com.example.simple_todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.simple_todo.modal.Word

class rv_main_adapter(var data: List<Word>,var model : WordViewModel): RecyclerView.Adapter<rv_main_adapter.customViewHolder>() {

    class customViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textView: TextView
        val button: Button
        var id: Int = 0
        init {
            textView = itemView.findViewById(R.id.tv_rv_item)
            button = itemView.findViewById(R.id.btn_rv_item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): customViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.rv_main_todo_item,parent,false)
        return  customViewHolder(view)
    }

    override fun onBindViewHolder(holder: customViewHolder, position: Int) {
        holder.textView.text = data[position].word
        holder.id = data[position].id
        holder.button.setOnClickListener {
            model.deleteWord(holder.id)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}