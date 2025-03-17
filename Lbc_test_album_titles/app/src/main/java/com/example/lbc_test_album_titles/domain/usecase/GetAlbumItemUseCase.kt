package com.example.lbc_test_album_titles.domain.usecase

import com.example.lbc_test_album_titles.domain.model.AlbumItem
import kotlinx.coroutines.flow.Flow

interface GetAlbumItemUseCase {
    suspend fun getAlbumItems() : Flow<List<AlbumItem>>
}