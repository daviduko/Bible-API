package com.example.bibleapi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bibleapi.databinding.ActivityBookLayoutBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class BookActivity : AppCompatActivity() {

    private val chaptersMap = mutableMapOf<Int, String>()
    private lateinit var spinner: Spinner
    private lateinit var binding: ActivityBookLayoutBinding
    private lateinit var book: Book
    private var verseList = mutableListOf<Verse>()
    private lateinit var adapterVerse: VerseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        book = intent.getSerializableExtra("book") as Book

        for (i in 1..book.chaptersCount) {
            chaptersMap[i] = "Chapter $i"
        }

        binding.button.setOnClickListener{
            val context = this
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }

        verseList.add(Verse(1, "1"))
        verseList.add(Verse(1, "1"))
        verseList.add(Verse(1, "1"))
        verseList.add(Verse(1, "1"))

        initVerseRecyclerView()
    }

    private fun initVerseRecyclerView(){
        adapterVerse = VerseAdapter(verseList)
        val recyclerView = binding.versesRecyclerView
        recyclerView.layoutManager = GridLayoutManager(this, 1)
        recyclerView.adapter = adapterVerse

        setSpinner()
    }

    private fun setSpinner(){
        spinner = binding.spinner
        val adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, chaptersMap.values.toList())
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinner.adapter = adapter
        spinner.setPrompt("Select a chapter");

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: android.view.View?,
                position: Int,
                id: Long
            ) {
                val selectedName = spinner.selectedItem.toString()
                val selectedId = chaptersMap.filterValues { it == selectedName }.keys.firstOrNull()
                Log.d("selected", selectedId.toString())
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val call: Response<List<Verse>> = ApiManager.apiService.getVerses(book.id, selectedId!!)
                        val verses: List<Verse>? = call.body()
                        Log.d("verse", verses!![0].verseText)

                        if(call.isSuccessful){
                            withContext(Dispatchers.Main){
                                verseList.clear()
                                verseList.addAll(verses!!)
                                adapterVerse.notifyDataSetChanged()
                                Log.d("change", verseList.size.toString())
                            }
                        }

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }
}