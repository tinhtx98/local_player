package com.tinhtx.feature.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.unit.dp
import com.tinhtx.domain.model.MediaItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val mediaList = viewModel.mediaList.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("TinhTX Player") })
        }
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(mediaList) { media ->
                MediaGridItem(media = media)
            }
        }
    }
}

@Composable
fun MediaGridItem(media: MediaItem) {
    // A simple representation of a media item in the grid
    Text(text = media.title)
}
