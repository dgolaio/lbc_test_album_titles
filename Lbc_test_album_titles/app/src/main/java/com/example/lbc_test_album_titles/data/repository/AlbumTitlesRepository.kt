package com.example.lbc_test_album_titles.data.repository

import com.example.lbc_test_album_titles.domain.model.AlbumItem

interface AlbumTitlesRepository {
    suspend fun fetchAlbumTitles() : List<AlbumItem>
}