package com.example.lbc_test_album_titles.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lbc_test_album_titles.domain.model.AlbumItem
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumsDAO {
    @Query("SELECT * FROM albums")
    fun getAllAlbums(): Flow<List<AlbumItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbums(albums: List<AlbumItem>)

    @Query("DELETE FROM albums")
    suspend fun clearAlbums()
}