package com.example.quicknote.screens.main

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.quicknote.R
import com.example.quicknote.models.AppNote
import com.example.quicknote.utilities.APP_ACTIVITY

class MainAdapter():RecyclerView.Adapter<MainAdapter.MainHolder>() {

    private var mListNotes = emptyList<AppNote>()

    class MainHolder(view: View):RecyclerView.ViewHolder(view) {
        val nameNote: TextView
        val textNote: TextView

        init {
            nameNote= view.findViewById(R.id.item_note_name)
            textNote = view.findViewById(R.id.item_note_text)
        }
    }

    override fun onViewAttachedToWindow(holder: MainHolder) {
        holder.itemView.setOnClickListener{
            MainFragment.onClick(mListNotes[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: MainHolder) {
        holder.itemView.setOnClickListener(null)
        holder.itemView.setOnLongClickListener(null)
        super.onViewDetachedFromWindow(holder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return MainHolder(view)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.nameNote.text = mListNotes[position].name
        holder.textNote.text = mListNotes[position].text
    }

    override fun getItemCount(): Int = mListNotes.size

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<AppNote>){
        mListNotes = list
        notifyDataSetChanged()
    }
}