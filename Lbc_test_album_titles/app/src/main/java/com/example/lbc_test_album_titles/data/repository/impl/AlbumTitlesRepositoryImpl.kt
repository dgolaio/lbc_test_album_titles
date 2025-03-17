package com.example.lbc_test_album_titles.data.repository.impl

import com.example.lbc_test_album_titles.data.api.ApiService
import com.example.lbc_test_album_titles.data.local.dao.AlbumsDAO
import com.example.lbc_test_album_titles.data.repository.AlbumTitlesRepository
import com.example.lbc_test_album_titles.domain.model.AlbumItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AlbumTitlesRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val albumsDAO: AlbumsDAO
) : AlbumTitlesRepository {

    //Gets from DAO
    override fun getAlbumTitles(): Flow<List<AlbumItem>> {
        return albumsDAO.getAllAlbums()
    }

    //get from APISERVICE
    override suspend fun fetchAlbumTitles() {
        try {
            val albumsFromApi = apiService.getAlbumTitles()
            albumsDAO.clearAlbums()
            albumsDAO.insertAlbums(albumsFromApi)
        } catch (_: Exception){
        }
    }

}