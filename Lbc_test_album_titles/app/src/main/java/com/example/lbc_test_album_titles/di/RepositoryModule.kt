package com.example.lbc_test_album_titles.di

import com.example.lbc_test_album_titles.data.api.ApiService
import com.example.lbc_test_album_titles.data.local.dao.AlbumsDAO
import com.example.lbc_test_album_titles.data.repository.AlbumTitlesRepository
import com.example.lbc_test_album_titles.data.repository.impl.AlbumTitlesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
object RepositoryModule {
    @Provides
    @Singleton
    fun provideRepository(
        apiService: ApiService,
        albumsDAO: AlbumsDAO
    ) : AlbumTitlesRepository {
        return AlbumTitlesRepositoryImpl(apiService,albumsDAO)
    }
}