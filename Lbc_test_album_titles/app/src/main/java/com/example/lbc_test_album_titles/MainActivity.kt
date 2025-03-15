package com.example.lbc_test_album_titles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lbc_test_album_titles.compose.BaseScreen
import com.example.lbc_test_album_titles.ui.theme.Lbc_test_album_titlesTheme
import com.example.lbc_test_album_titles.viewmodel.AlbumTitlesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lbc_test_album_titlesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BaseScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val viewModel: AlbumTitlesViewModel = hiltViewModel()
    val tests by viewModel.albumItems.collectAsState()

    /*LaunchedEffect(Unit) {
        viewModel.loadAlbumTitles()
    }*/

    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lbc_test_album_titlesTheme {
        Greeting("Android")
    }
}