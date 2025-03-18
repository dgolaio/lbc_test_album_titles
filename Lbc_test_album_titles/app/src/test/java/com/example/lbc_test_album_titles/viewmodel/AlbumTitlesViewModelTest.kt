package com.example.lbc_test_album_titles.viewmodel

import com.example.lbc_test_album_titles.domain.model.AlbumItem
import com.example.lbc_test_album_titles.domain.usecase.GetAlbumItemUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class AlbumTitlesViewModelTest {

    private lateinit var useCase: GetAlbumItemUseCase
    private lateinit var viewModel: AlbumTitlesViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        // has to put a test Dispatcher since,
        // some functions of the VM call Coroutines
        Dispatchers.setMain(testDispatcher)
        useCase = mock(GetAlbumItemUseCase::class.java)
        viewModel = AlbumTitlesViewModel(useCase)
    }

    @After
    fun tearDown() {
        //I done this to reset the Dispatcher and dont affect
        //other possible tests ( since we are running a test
        //Dispacher
        Dispatchers.resetMain()
    }

    @Test
    fun `loadAlbumTitles should update albumItems`() = runTest {
        // List to return no Mock a Result of a Method
        val albumList = listOf(AlbumItem(1, 1, "Album Title", "http:test.lbc", "http:test_thumb.lbc"))

        // Make that the function to get Albums returns
        //Mocked List
        `when`(useCase.getAlbumItems()).thenReturn(flowOf(albumList))

        // Exe the fuction
        viewModel.loadAlbumTitles()

        //"Advances time" of the coroutin inside the viewModel
        // since it is a mocked environment with a test dispacher
        testDispatcher.scheduler.advanceUntilIdle()

        // Validates both values are equal (
        // the one we mocked to return in the method and the Stored
        //inside the VM
        assertEquals(albumList, viewModel.albumItems.value)
    }

    @Test
    fun `loadAlbumTitles should handle exceptions gracefully`() = runTest {
        // Mock response with Exception
        `when`(useCase.getAlbumItems()).thenThrow(RuntimeException("Error loading albums"))

        // Exe method
        viewModel.loadAlbumTitles()

        // The State of the Album List must remain Empty
        assertTrue(viewModel.albumItems.value.isEmpty())
    }
}