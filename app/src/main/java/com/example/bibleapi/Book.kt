package com.example.bibleapi

import java.io.Serializable

class Book(
    val id: Int,
    val name: String,
    val testament: String
) : Serializable {
    var chaptersCount: Int = 0

}
