package com.example.bibleapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class VerseAdapter(var verses : List<Verse>) : RecyclerView.Adapter<VerseViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return VerseViewHolder(layoutInflater.inflate(R.layout.verse_group_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return verses.size
    }

    override fun onBindViewHolder(holder: VerseViewHolder, position: Int) {
        val verse = verses[position]
        holder.bind(verse)
    }
}