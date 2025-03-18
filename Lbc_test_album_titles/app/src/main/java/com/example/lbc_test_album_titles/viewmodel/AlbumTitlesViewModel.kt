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

/**
 * @author : Duarte Golaio
 * @mailto : duarte_golaio@hotmail.com
 * @created : 14/03/2025
 **/

/**
 * ViewModel Class AlbumTitlesViewModel.
 * A class to manage the two way bridge between de View and the model
 * and its states
 */

@HiltViewModel
class AlbumTitlesViewModel @Inject constructor(
    private val getAlbumItemUseCase: GetAlbumItemUseCase
) : ViewModel() {

    private val _albumItems = MutableStateFlow<List<AlbumItem>>(arrayListOf())
    val albumItems: StateFlow<List<AlbumItem>> = _albumItems

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    /* Launch this at the initialization of the VM */
   init {
        loadAlbumTitles()
    }

    fun loadAlbumTitles() {
        /* launch the coroutine */
        viewModelScope.launch {
            try {
                    _isRefreshing.value = true
                    getAlbumItemUseCase.getAlbumItems().collect { albumList ->
                    _albumItems.value = albumList
                     delay(200) //added this just for the visual efect, because the info is downloaded fast
                    _isRefreshing.value = false
                }
            } catch (e:Exception) {
                e.printStackTrace() // TODO: Manage posible error
            }
        }
    }
}