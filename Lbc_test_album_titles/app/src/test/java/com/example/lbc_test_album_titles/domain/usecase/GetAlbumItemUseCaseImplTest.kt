package com.example.lbc_test_album_titles.domain.usecase

import com.example.lbc_test_album_titles.data.repository.AlbumTitlesRepository
import com.example.lbc_test_album_titles.domain.model.AlbumItem
import com.example.lbc_test_album_titles.domain.usecase.impl.GetAlbumItemUseCaseImpl
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`


class GetAlbumItemUseCaseImplTest {

    private lateinit var repository: AlbumTitlesRepository
    private lateinit var useCase: GetAlbumItemUseCaseImpl

    @Before
    fun setup() {
        repository = mock(AlbumTitlesRepository::class.java)
        useCase = GetAlbumItemUseCaseImpl(repository)
    }

    @Test
    fun `when calling getAlbumItems must return albumList`() = runTest {
        // Create the album List to Mock response
        val albumList = listOf(AlbumItem(1, 1, "Album Title","http:test.lbc","http:test_thumb.lbc"))

        // Mock the response with the list above
        `when`(repository.getAlbumTitles()).thenReturn(flowOf(albumList))

        // Execute the function an get the first value (mocked)
        val result = useCase.getAlbumItems().first()

        // validate that the fetch Album was called at least once
        verify(repository).fetchAlbumTitles()

        // Validate that the values are equall
        assertEquals(albumList, result)
    }

    @Test
    fun `when fetchAlbumTitles throws an exception, getAlbumItems should still return albumList`() = runTest {
        val albumList = listOf(AlbumItem(1, 1, "Album Title", "http:test.lbc", "http:test_thumb.lbc"))

        // emulate exception whe calling the repository function
        `when`(repository.fetchAlbumTitles()).thenThrow(RuntimeException("Network Error"))

        // emulate that the dataBase sthill returns
        //values
        `when`(repository.getAlbumTitles()).thenReturn(flowOf(albumList))

        // Execute the UseCase and get the first value
        val result = useCase.getAlbumItems().first()

        // Validate that the values are still equal,
        // even thought there was an exception in fetching new Albums
        assertEquals(albumList, result)
    }
}