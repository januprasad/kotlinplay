package com.example.koin_test

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.koin_test.data.Post

@Composable
fun PostScreen(uiState: AppState.UiState) {
    when (uiState.loading) {
        true ->
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }

        false -> {
            uiState.data?.let {
                LazyColumn {
                    items(it) {
                        PostItem(it)
                    }
                }
            }
        }
    }
}

@Composable
fun PostItem(post: Post) {
    Card(Modifier.padding(16.dp)) {
        Column(
            Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = post.title,
                fontFamily = FontFamily.SansSerif,
                fontSize = 30.sp,
                lineHeight = 40.sp,
            )
            Text(text = post.body)
        }
    }
}
