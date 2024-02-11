package com.example.bibleapi

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BooksAdapter(var books: List<Book>) : RecyclerView.Adapter<BooksViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BooksViewHolder(layoutInflater.inflate(R.layout.book_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        val book = books[position]
        holder.bind(book)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, BookActivity::class.java)
            intent.putExtra("book", book)
            context.startActivity(intent)
        }
    }

}