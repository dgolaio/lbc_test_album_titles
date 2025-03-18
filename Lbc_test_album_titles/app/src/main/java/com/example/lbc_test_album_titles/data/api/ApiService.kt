package com.example.lbc_test_album_titles.data.api

import com.example.lbc_test_album_titles.domain.model.AlbumItem
import retrofit2.http.GET
import retrofit2.http.Headers

/**
 * @author : Duarte Golaio
 * @mailto : duarte_golaio@hotmail.com
 * @created : 14/03/2025
 **/

/**
 * Simple API Interface to GET from endPoint ( Json File )
 */

interface ApiService {

    @Headers("User-Agent: MyAlbumTitlesApp/1.0")
    @GET("technical-test.json")
    suspend fun getAlbumTitles() : List<AlbumItem>

}