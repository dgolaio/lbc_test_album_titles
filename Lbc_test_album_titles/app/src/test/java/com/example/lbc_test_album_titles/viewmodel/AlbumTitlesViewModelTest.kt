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
        Dispatchers.setMain(testDispatcher)
        useCase = mock(GetAlbumItemUseCase::class.java)
        viewModel = AlbumTitlesViewModel(useCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadAlbumTitles should update albumItems`() = runTest {
        // Simular uma lista de álbuns
        val albumList = listOf(AlbumItem(1, 1, "Album Title", "http:test.lbc", "http:test_thumb.lbc"))

        // Configurar o mock do UseCase sem `whenever`
        `when`(useCase.getAlbumItems()).thenReturn(flowOf(albumList))

        // Executar o método que carrega os álbuns
        viewModel.loadAlbumTitles()

        // Avança o tempo para que a coroutine dentro da ViewModel execute
        testDispatcher.scheduler.advanceUntilIdle()

        // Verificar se o StateFlow foi atualizado corretamente
        assertEquals(albumList, viewModel.albumItems.value)
    }

    @Test
    fun `isRefreshing should be true while loading and false after completion`() = runTest {
        // Criar um fluxo suspenso para simular atraso no carregamento
        val albumFlow = MutableStateFlow(emptyList<AlbumItem>())
        `when`(useCase.getAlbumItems()).thenReturn(albumFlow)

        // Executar o método
        viewModel.loadAlbumTitles()

        // No início, `isRefreshing` deve ser true
        assertTrue(viewModel.isRefreshing.value)

        // Atualizar o fluxo e esperar o processamento
        albumFlow.value = listOf(AlbumItem(1, 1, "Album Title", "http:test.lbc", "http:test_thumb.lbc"))
        advanceUntilIdle()

        // Depois da atualização, `isRefreshing` deve ser false
        assertFalse(viewModel.isRefreshing.value)
    }

    @Test
    fun `loadAlbumTitles should handle exceptions gracefully`() = runTest {
        // Simular um erro no UseCase sem `whenever`
        `when`(useCase.getAlbumItems()).thenThrow(RuntimeException("Error loading albums"))

        // Executar o método
        viewModel.loadAlbumTitles()

        // O estado da lista de álbuns deve continuar vazio
        assertTrue(viewModel.albumItems.value.isEmpty())
    }
}