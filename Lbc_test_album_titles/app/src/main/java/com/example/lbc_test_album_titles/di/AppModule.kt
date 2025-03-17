package com.example.lbc_test_album_titles.di

import android.content.Context
import androidx.room.Room
import com.example.lbc_test_album_titles.data.api.ApiService
import com.example.lbc_test_album_titles.data.local.dao.AlbumsDAO
import com.example.lbc_test_album_titles.data.local.database.AlbumsAppDataBase
import com.example.lbc_test_album_titles.data.repository.AlbumTitlesRepository
import com.example.lbc_test_album_titles.data.repository.impl.AlbumTitlesRepositoryImpl
import com.example.lbc_test_album_titles.domain.usecase.GetAlbumItemUseCase
import com.example.lbc_test_album_titles.domain.usecase.impl.GetAlbumItemUseCaseImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://static.leboncoin.fr/img/shared/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideAPIService(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(
        apiService: ApiService,
        albumsDAO: AlbumsDAO
    ) : AlbumTitlesRepository {
        return AlbumTitlesRepositoryImpl(apiService,albumsDAO)
    }

    @Provides
    @Singleton
    fun provideUseCase(
        repository: AlbumTitlesRepository
    ) : GetAlbumItemUseCase {
        return GetAlbumItemUseCaseImpl(repository)
    }

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