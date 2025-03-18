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
        apiService = mock(ApiService::class.java)
        albumsDAO = mock(AlbumsDAO::class.java)
        repository = AlbumTitlesRepositoryImpl(apiService, albumsDAO)
    }

    @Test
    fun `getAlbumTitles should return data from DAO`() = runTest {
        // Simular uma lista de álbuns armazenados no Room
        val albumList = listOf(AlbumItem(1, 1, "Album Title", "http:test.lbc", "http:test_thumb.lbc"))

        // Simular a resposta do DAO
        `when`(albumsDAO.getAllAlbums()).thenReturn(flowOf(albumList))

        // Executar o método do repositório
        val result = repository.getAlbumTitles().first()

        // Verificar se o resultado corresponde ao esperado
        assertEquals(albumList, result)
    }

    @Test
    fun `fetchAlbumTitles should fetch from API and store in DAO`() = runTest {
        // Simular resposta da API
        val albumList = listOf(AlbumItem(1, 1, "Album Title", "http:test.lbc", "http:test_thumb.lbc"))

        // Quando a API for chamada, retornar a lista simulada
        `when`(apiService.getAlbumTitles()).thenReturn(albumList)

        // Executar o método
        repository.fetchAlbumTitles()

        // Verificar se os métodos do DAO foram chamados corretamente
        verify(albumsDAO).clearAlbums() // Deve limpar os álbuns antes de salvar novos
        verify(albumsDAO).insertAlbums(albumList) // Deve inserir os novos álbuns
    }

    @Test
    fun `fetchAlbumTitles should handle exceptions gracefully`() = runTest {
        // Simular uma exceção na API
        `when`(apiService.getAlbumTitles()).thenThrow(RuntimeException("API Error"))

        // Executar o método
        repository.fetchAlbumTitles()

        // Verificar que o DAO *não* tentou limpar ou salvar dados
        verify(albumsDAO, never()).clearAlbums()
        verify(albumsDAO, never()).insertAlbums(anyList())
    }
}