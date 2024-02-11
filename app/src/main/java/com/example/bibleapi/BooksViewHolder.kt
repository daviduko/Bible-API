package com.example.bibleapi

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.bibleapi.databinding.BookLayoutBinding

class BooksViewHolder(view : View) : RecyclerView.ViewHolder(view) {

    val binding = BookLayoutBinding.bind(view)

    fun bind(book : Book){
        binding.bookTitle.text = book.name
    }
}