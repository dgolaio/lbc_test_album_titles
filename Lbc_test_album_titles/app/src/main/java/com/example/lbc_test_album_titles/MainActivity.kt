package com.example.lbc_test_album_titles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.lbc_test_album_titles.compose.BaseScreen
import com.example.lbc_test_album_titles.ui.theme.Lbc_test_album_titlesTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Simple Main Activity with BaseScreen Composable
 */

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