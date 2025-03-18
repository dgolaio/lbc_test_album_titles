package com.example.lbc_test_album_titles.data.repository

import com.example.lbc_test_album_titles.data.api.ApiService
import com.example.lbc_test_album_titles.data.local.dao.AlbumsDAO
import com.example.lbc_test_album_titles.data.repository.impl.AlbumTitlesRepositoryImpl
import com.example.lbc_test_album_titles.domain.model.AlbumItem
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class AlbumTitlesRepositoryImplTest {

    private lateinit var apiService: ApiService
    private lateinit var albumsDAO: AlbumsDAO
    private lateinit var repository: AlbumTitlesRepositoryImpl

    @Before
    fun setup() {
        /* mocking with mockito all the necesary clases to tests */
        apiService = mock(ApiService::class.java)
        albumsDAO = mock(AlbumsDAO::class.java)
        repository = AlbumTitlesRepositoryImpl(apiService, albumsDAO)
    }

    @Test
    fun `getAlbumTitles should return data from DAO`() = runTest {

        // Simulate a list of albums stored in Room
        val albumList = listOf(AlbumItem(1, 1, "Album Title", "http:test.lbc", "http:test_thumb.lbc"))

        // Simulate (albumsDAO.getAllAlbums()) the answer of the DAO
        `when`(albumsDAO.getAllAlbums()).thenReturn(flowOf(albumList))

        // execute repo method ( with DAO mocked values )
        val result = repository.getAlbumTitles().first()

        // Assert that these values are equal ( the one we mocked
        // into DAO and the one we got from the repo function
        assertEquals(albumList, result)
    }

    @Test
    fun `fetchAlbumTitles should fetch from API and store in DAO`() = runTest {

        // Simulate a list of albums stored in Room
        val albumList = listOf(AlbumItem(1, 1, "Album Title", "http:test.lbc", "http:test_thumb.lbc"))

        // mock the return of the apiServices List
        `when`(apiService.getAlbumTitles()).thenReturn(albumList)

        // Exe the method to test
        repository.fetchAlbumTitles()

        // Validate if both these methods were called at least
        // once
        verify(albumsDAO).clearAlbums()
        verify(albumsDAO).insertAlbums(albumList)
    }

    @Test
    fun `fetchAlbumTitles should handle exceptions gracefully`() = runTest {
        // Simulate the API CALL with Exception
        `when`(apiService.getAlbumTitles()).thenThrow(RuntimeException("API Error"))

        // Exe method
        repository.fetchAlbumTitles()

        // Since its thrown an exception,
        // we validate that this DAO must not clean and insert
        // any values
        verify(albumsDAO, never()).clearAlbums()
        verify(albumsDAO, never()).insertAlbums(anyList())
    }
}