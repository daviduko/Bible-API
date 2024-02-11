package com.example.bibleapi

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.bibleapi.databinding.VerseGroupLayoutBinding

class VerseViewHolder (view : View) : RecyclerView.ViewHolder(view) {
    val binding = VerseGroupLayoutBinding.bind(view)

    fun bind(verse: Verse){
        Log.d("VerseViewHolder", "Verse ID: ${verse.id}, Text: ${verse.verseText}")
        binding.verseTitle.text = "Verse ${verse.id}"
        binding.verseText.text = verse.verseText
    }
}