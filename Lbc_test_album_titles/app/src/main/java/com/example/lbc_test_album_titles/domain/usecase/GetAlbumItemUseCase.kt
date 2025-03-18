package com.example.lbc_test_album_titles.domain.usecase

import com.example.lbc_test_album_titles.domain.model.AlbumItem
import kotlinx.coroutines.flow.Flow

/**
 * @author : Duarte Golaio
 * @mailto : duarte_golaio@hotmail.com
 * @created : 14/03/2025
 **/

/**
 * Interface of the Use Case to Get Albums ( Domain Layer )
 */

interface GetAlbumItemUseCase {
    suspend fun getAlbumItems() : Flow<List<AlbumItem>>
}