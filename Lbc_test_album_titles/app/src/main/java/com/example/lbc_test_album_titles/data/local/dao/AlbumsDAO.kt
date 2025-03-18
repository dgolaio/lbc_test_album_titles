package com.example.lbc_test_album_titles.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lbc_test_album_titles.domain.model.AlbumItem
import kotlinx.coroutines.flow.Flow

/**
 * @author : Duarte Golaio
 * @mailto : duarte_golaio@hotmail.com
 * @created : 14/03/2025
 **/

/**
 * Interface of DAO with access methods necesary to Room cache
 */

@Dao
interface AlbumsDAO {
    @Query("SELECT * FROM albums")
    fun getAllAlbums(): Flow<List<AlbumItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbums(albums: List<AlbumItem>)

    @Query("DELETE FROM albums")
    suspend fun clearAlbums()
}