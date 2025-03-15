package com.example.lbc_test_album_titles.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun AlbumItemCompose(modifier: Modifier = Modifier, albumTitle: String, imageUrl:String) {
    Box(modifier = modifier
        .fillMaxWidth(1f)
        .border(BorderStroke(1.dp, Color.DarkGray))
        .padding(20.dp)
        ,contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier
                .fillMaxWidth(1f)
                .background(Color.DarkGray)
            ){
                Text(modifier = Modifier, text = albumTitle, textAlign = TextAlign.Center, fontSize = 24.sp, color = Color.White, fontFamily = FontFamily.Monospace)
            }

            Spacer(modifier = Modifier.height(20.dp))
            Box(modifier = Modifier.border(BorderStroke(2.dp, Color.DarkGray))){
                AsyncImage(model = imageUrl, contentDescription = "$albumTitle + Image", alignment = Alignment.Center)
            }
        }
    }
}