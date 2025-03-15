package com.example.lbc_test_album_titles.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lbc_test_album_titles.data.repository.AlbumTitlesRepository
import com.example.lbc_test_album_titles.data.repository.impl.AlbumTitlesRepositoryImpl
import com.example.lbc_test_album_titles.domain.model.AlbumItem
import com.example.lbc_test_album_titles.domain.usecase.GetAlbumItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumTitlesViewModel @Inject constructor(
    private val getAlbumItemUseCase: GetAlbumItemUseCase
) : ViewModel() {

    private val _albumItems = MutableStateFlow<List<AlbumItem>>(arrayListOf())
    val albumItems: StateFlow<List<AlbumItem>> = _albumItems

   init {
        loadAlbumTitles()
    }

    fun loadAlbumTitles() {
        viewModelScope.launch {
            try {
                val albumItem = getAlbumItemUseCase.getAlbumItems()
                _albumItems.value = albumItem
            } catch (e:Exception) {
                e.printStackTrace()
            }
        }
    }
}