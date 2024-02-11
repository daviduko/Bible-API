package com.example.bibleapi

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface ApiService {
    @GET("books")
    suspend fun getBooks() : Response<List<Book>>

    @GET("books/{id}/chapters")
    suspend fun getChapters(@Path("id") id : Int) : Response<List<ChaptersResponse>>

    @GET("books/{bookId}/chapters/{id}")
    suspend fun getVerses(@Path("bookId") bookId: Int, @Path("id") id: Int) : Response<List<Verse>>
}