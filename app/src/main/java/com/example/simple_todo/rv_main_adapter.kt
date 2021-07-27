package com.example.simple_todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class rv_main_adapter(val data: ArrayList<String>): RecyclerView.Adapter<rv_main_adapter.customViewHolder>() {
    class customViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textView: TextView
        init {
            textView = itemView.findViewById(R.id.tv_rv_item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): customViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.rv_main_todo_item,parent,false)
        return  customViewHolder(view)
    }

    override fun onBindViewHolder(holder: customViewHolder, position: Int) {
        holder.textView.text = data[position]
    }

    override fun getItemCount(): Int {
        return data.size
    }
}