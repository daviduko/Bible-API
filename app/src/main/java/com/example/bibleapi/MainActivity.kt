package com.example.bibleapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.bibleapi.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: BooksAdapter
    private val booksList = mutableListOf<Book>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showBooks()
    }

    private fun showBooks() {
        adapter = BooksAdapter(booksList)
        val recyclerView = binding.recyclerViewBooks
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val call: Response<List<Book>> = ApiManager.apiService.getBooks()
                val books: List<Book>? = call.body()

                if (call.isSuccessful && books != null) {
                    for (book in books) {
                        val chaptersResponse = ApiManager.apiService.getChapters(book.id)
                        if (chaptersResponse.isSuccessful) {
                            book.chaptersCount = chaptersResponse.body()?.size ?: 0
                        }
                    }

                    withContext(Dispatchers.Main) {
                        booksList.addAll(books)
                        adapter.notifyDataSetChanged()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}