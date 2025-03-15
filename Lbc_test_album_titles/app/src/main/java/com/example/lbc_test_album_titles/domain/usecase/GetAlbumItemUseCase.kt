package com.example.lbc_test_album_titles.domain.usecase

import com.example.lbc_test_album_titles.domain.model.AlbumItem

interface GetAlbumItemUseCase {
    suspend fun getAlbumItems() : List<AlbumItem>
}