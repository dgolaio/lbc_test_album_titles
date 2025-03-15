package com.example.lbc_test_album_titles.domain.usecase.impl

import com.example.lbc_test_album_titles.data.repository.AlbumTitlesRepository
import com.example.lbc_test_album_titles.data.repository.impl.AlbumTitlesRepositoryImpl
import com.example.lbc_test_album_titles.domain.model.AlbumItem
import com.example.lbc_test_album_titles.domain.usecase.GetAlbumItemUseCase
import javax.inject.Inject

class GetAlbumItemUseCaseImpl @Inject constructor(
    private val repository: AlbumTitlesRepository
): GetAlbumItemUseCase {

    override suspend fun getAlbumItems(): List<AlbumItem> {
        return repository.fetchAlbumTitles()
    }

}