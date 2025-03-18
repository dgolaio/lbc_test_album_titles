package com.example.lbc_test_album_titles.di

import android.content.Context
import androidx.room.Room
import com.example.lbc_test_album_titles.data.local.dao.AlbumsDAO
import com.example.lbc_test_album_titles.data.local.database.AlbumsAppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author : Duarte Golaio
 * @mailto : duarte_golaio@hotmail.com
 * @created : 18/03/2025
 **/

/**
 * API Module D.I
 */

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AlbumsAppDataBase {
        return Room.databaseBuilder(
            context,
            AlbumsAppDataBase::class.java,
            "album_app_database"
        ).build()
    }

    @Provides
    fun provideAlbumDao(database: AlbumsAppDataBase): AlbumsDAO {
        return database.albumDao()
    }
}