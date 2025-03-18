package com.example.lbc_test_album_titles.data.repository.impl

import com.example.lbc_test_album_titles.data.api.ApiService
import com.example.lbc_test_album_titles.data.local.dao.AlbumsDAO
import com.example.lbc_test_album_titles.data.repository.AlbumTitlesRepository
import com.example.lbc_test_album_titles.domain.model.AlbumItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author : Duarte Golaio
 * @mailto : duarte_golaio@hotmail.com
 * @created : 15/03/2025
 **/

/**
 * Repository to make requests to api and DAO
 */

class AlbumTitlesRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val albumsDAO: AlbumsDAO
) : AlbumTitlesRepository {

    /*Gets from DAO: since its from DB flow, if the values change the it actualizes the set of data*/
    override fun getAlbumTitles(): Flow<List<AlbumItem>> {
        return albumsDAO.getAllAlbums()
    }

    /*get from APISERVICE all the albums, resets the dataSet in db in DAO and inserts new set of data from what it gets from the API*/
    override suspend fun fetchAlbumTitles() {
        try {
            val albumsFromApi = apiService.getAlbumTitles()
            albumsDAO.clearAlbums()
            albumsDAO.insertAlbums(albumsFromApi)
        } catch (e: Exception){
            e.printStackTrace()
            //TODO: Something to handle errors to try to access the api?
        }
    }

}