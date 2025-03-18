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
        // Criamos uma lista de álbuns simulada
        val albumList = listOf(AlbumItem(1, 1, "Album Title","http:test.lbc","http:test_thumb.lbc"))

        // Dizemos ao Mockito que quando chamarmos repository.getAlbumTitles(), deve retornar albumList
        `when`(repository.getAlbumTitles()).thenReturn(flowOf(albumList))

        // Executamos o Use Case
        val result = useCase.getAlbumItems().first()

        // Verificamos que repository.fetchAlbumTitles() foi chamado
        verify(repository).fetchAlbumTitles()

        // Verificamos se o resultado retornado é igual ao esperado
        assertEquals(albumList, result)
    }

    @Test
    fun `when fetchAlbumTitles throws an exception, getAlbumItems should still return albumList`() = runTest {
        val albumList = listOf(AlbumItem(1, 1, "Album Title", "http:test.lbc", "http:test_thumb.lbc"))

        // Simular erro ao tentar atualizar os álbuns
        `when`(repository.fetchAlbumTitles()).thenThrow(RuntimeException("Network Error"))

        // O banco de dados local ainda deve devolver os álbuns
        `when`(repository.getAlbumTitles()).thenReturn(flowOf(albumList))

        // Executamos o Use Case
        val result = useCase.getAlbumItems().first()

        // Verificamos se o resultado ainda é válido, mesmo com erro na atualização
        assertEquals(albumList, result)
    }
}