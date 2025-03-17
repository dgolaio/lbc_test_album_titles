package com.example.lbc_test_album_titles.data.repository

import com.example.lbc_test_album_titles.domain.model.AlbumItem
import kotlinx.coroutines.flow.Flow

interface AlbumTitlesRepository {
    fun getAlbumTitles() : Flow<List<AlbumItem>>
    suspend fun fetchAlbumTitles() //to try to refresh albums
}