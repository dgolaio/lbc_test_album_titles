package com.example.lbc_test_album_titles.di

import com.example.lbc_test_album_titles.data.repository.AlbumTitlesRepository
import com.example.lbc_test_album_titles.domain.usecase.GetAlbumItemUseCase
import com.example.lbc_test_album_titles.domain.usecase.impl.GetAlbumItemUseCaseImpl
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
object UseCaseModule {
    @Provides
    @Singleton
    fun provideUseCase(
        repository: AlbumTitlesRepository
    ) : GetAlbumItemUseCase {
        return GetAlbumItemUseCaseImpl(repository)
    }
}