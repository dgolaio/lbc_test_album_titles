package com.example.lbc_test_album_titles.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lbc_test_album_titles.viewmodel.AlbumTitlesViewModel

@Composable
fun BaseScreen(modifier: Modifier = Modifier,  viewModel: AlbumTitlesViewModel = hiltViewModel()) {
    val albumItems by viewModel.albumItems.collectAsState()

    LazyColumn (modifier = modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
    ) {
        items(albumItems.size) { albumPos ->
            AlbumItemCompose(albumTitle = albumItems.get(albumPos).title, imageUrl = albumItems.get(albumPos).imageUrl)
        }
    }
}