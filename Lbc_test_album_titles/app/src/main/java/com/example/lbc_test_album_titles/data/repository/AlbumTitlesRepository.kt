package com.example.lbc_test_album_titles.data.repository

import com.example.lbc_test_album_titles.domain.model.AlbumItem
import kotlinx.coroutines.flow.Flow

/**
 * @author : Duarte Golaio
 * @mailto : duarte_golaio@hotmail.com
 * @created : 15/03/2025
 **/

/**
 * Interface of the AlbumTitlesRepository (data layer) .
 *  functions to return all the List of AlbumItems(getAlbumTitles())
 *  and make requests to API and save in DB
 */

interface AlbumTitlesRepository {
    fun getAlbumTitles() : Flow<List<AlbumItem>>
    suspend fun fetchAlbumTitles() //to try to refresh albums
}