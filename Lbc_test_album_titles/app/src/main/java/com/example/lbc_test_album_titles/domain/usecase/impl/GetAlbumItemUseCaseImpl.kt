package com.example.lbc_test_album_titles.domain.usecase.impl

import com.example.lbc_test_album_titles.data.repository.AlbumTitlesRepository
import com.example.lbc_test_album_titles.domain.model.AlbumItem
import com.example.lbc_test_album_titles.domain.usecase.GetAlbumItemUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author : Duarte Golaio
 * @mailto : duarte_golaio@hotmail.com
 * @created : 14/03/2025
 **/

/**
 * Implementation of the UseCase (domain layer)  to fetch and refetch the
 * values of the album and save them in cache and get the from
 * Cache.
 */

class GetAlbumItemUseCaseImpl @Inject constructor(
    private val repository: AlbumTitlesRepository
): GetAlbumItemUseCase {

    override suspend fun getAlbumItems(): Flow<List<AlbumItem>> {

        try {
            // In here i do/redo the request, in case its necesary the album Titles (API)
            repository.fetchAlbumTitles()
        } catch (exception:Exception) {
            exception.printStackTrace()
            //In Case netFails or exception in trying to refresh list Of albums
            //TODO: Make action
        }

        val albumEntities = repository.getAlbumTitles()

        return albumEntities
    }
}