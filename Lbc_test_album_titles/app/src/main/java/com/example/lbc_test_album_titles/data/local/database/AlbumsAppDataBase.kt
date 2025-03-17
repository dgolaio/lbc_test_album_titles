package com.example.lbc_test_album_titles.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lbc_test_album_titles.data.local.dao.AlbumsDAO
import com.example.lbc_test_album_titles.domain.model.AlbumItem

@Database(entities = [AlbumItem::class], version = 1, exportSchema = false)
abstract class AlbumsAppDataBase : RoomDatabase() {
    abstract fun albumDao() : AlbumsDAO
}