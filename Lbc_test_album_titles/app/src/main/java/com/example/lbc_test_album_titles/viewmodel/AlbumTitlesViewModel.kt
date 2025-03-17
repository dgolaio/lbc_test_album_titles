package com.example.lbc_test_album_titles.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lbc_test_album_titles.domain.model.AlbumItem
import com.example.lbc_test_album_titles.domain.usecase.GetAlbumItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumTitlesViewModel @Inject constructor(
    private val getAlbumItemUseCase: GetAlbumItemUseCase
) : ViewModel() {

    //TODO: Prepare Tests; Prepare Documents

    private val _albumItems = MutableStateFlow<List<AlbumItem>>(arrayListOf())
    val albumItems: StateFlow<List<AlbumItem>> = _albumItems

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

   init {
        loadAlbumTitles()
    }

    fun loadAlbumTitles() {
        viewModelScope.launch {
            try {
                    _isRefreshing.value = true
                    getAlbumItemUseCase.getAlbumItems().collect { albumList ->
                    _albumItems.value = albumList
                     delay(200) //added this just for the visual efect, because the info is downloaded fast
                    _isRefreshing.value = false
                }
            } catch (e:Exception) {
                e.printStackTrace()
            }
        }
    }
}