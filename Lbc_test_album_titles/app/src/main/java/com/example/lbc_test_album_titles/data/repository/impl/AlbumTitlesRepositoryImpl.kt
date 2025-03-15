package com.example.lbc_test_album_titles.data.repository.impl

import com.example.lbc_test_album_titles.data.api.ApiService
import com.example.lbc_test_album_titles.data.repository.AlbumTitlesRepository
import com.example.lbc_test_album_titles.domain.model.AlbumItem
import javax.inject.Inject

class AlbumTitlesRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : AlbumTitlesRepository {
    override suspend fun fetchAlbumTitles(): List<AlbumItem> {
        return apiService.getAlbumTitles()
    }
}