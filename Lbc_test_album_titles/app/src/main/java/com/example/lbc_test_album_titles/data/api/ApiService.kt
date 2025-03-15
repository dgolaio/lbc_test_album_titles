package com.example.lbc_test_album_titles.data.api

import com.example.lbc_test_album_titles.domain.model.AlbumItem
import retrofit2.http.GET

interface ApiService {

    @GET("technical-test.json")
    suspend fun getAlbumTitles() : List<AlbumItem>

}