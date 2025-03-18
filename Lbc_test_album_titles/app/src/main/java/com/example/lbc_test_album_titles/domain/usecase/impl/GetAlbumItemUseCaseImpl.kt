package com.example.lbc_test_album_titles.domain.usecase.impl

import com.example.lbc_test_album_titles.data.repository.AlbumTitlesRepository
import com.example.lbc_test_album_titles.domain.model.AlbumItem
import com.example.lbc_test_album_titles.domain.usecase.GetAlbumItemUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAlbumItemUseCaseImpl @Inject constructor(
    private val repository: AlbumTitlesRepository
): GetAlbumItemUseCase {

    override suspend fun getAlbumItems(): Flow<List<AlbumItem>> {

        try {
            // In here i refresh, in case its necesary the album Titles (API)
            repository.fetchAlbumTitles()
        } catch (exception:Exception) {
            //In Case netFails or exception in trying to refresh list Of albums
        }

        val albumEntities = repository.getAlbumTitles()

        return albumEntities
    }
}