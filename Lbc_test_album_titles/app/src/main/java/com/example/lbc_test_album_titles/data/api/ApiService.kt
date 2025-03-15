package com.example.lbc_test_album_titles.data.api

import com.example.lbc_test_album_titles.domain.model.AlbumItem
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @Headers("User-Agent: MyAlbumTitlesApp/1.0")
    @GET("technical-test.json")
    suspend fun getAlbumTitles() : List<AlbumItem>

}