package com.nasagallery.view.activity

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.nasagallery.data.model.NASAPhotoItem

@Composable
fun NasaDetailsScreen(
    navController: NavController,
    nasaPhotoItem: NASAPhotoItem
) {


    // State to track the scroll position
    val scrollState = rememberLazyListState()
    val scrollOffset = remember { derivedStateOf { scrollState.firstVisibleItemScrollOffset.toFloat() } }

    // Smoothly animate the alpha and translationY (fade and slide effect)
    val imageAlpha by animateFloatAsState(targetValue = (1 - (scrollOffset.value / 300)).coerceIn(0f, 1f),
        label = "image alpha"
    )
    val imageTranslationY by animateFloatAsState(targetValue = (-scrollOffset.value / 2).coerceIn(-150f, 0f),
        label = "image translation"
    )

    Scaffold(
        topBar = {
            NasaToolbar(
                title = "Details",
                onBackClick = { navController.popBackStack() }
            )
        },
        content = { innerPadding ->

            LazyColumn(
                state = scrollState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                // Top Image
                item {
                    AsyncImage(
                        model = nasaPhotoItem.url,
                        contentDescription = "NASA Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .graphicsLayer {
                                alpha = imageAlpha
                                translationY = imageTranslationY
                            },
                        contentScale = ContentScale.Crop
                    )
                }

                // Title
                item {
                    Text(
                        text = nasaPhotoItem.title,
                        style = MaterialTheme.typography.titleLarge.copy(fontSize = 24.sp),
                        modifier = Modifier
                            .padding(16.dp),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                // Date
                item {
                    Text(
                        text = nasaPhotoItem.date,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 4.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                // Description
                item {
                    Text(
                        text = nasaPhotoItem.explanation,
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                        modifier = Modifier
                            .padding(16.dp)
                    )
                }

                item {
                    Text(
                        text = "Copyright: " + nasaPhotoItem.copyright,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(12.dp)
                    )
                }

                // Add extra spacing at the bottom for better scrolling
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }


            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NasaToolbar(
    title: String,
    onBackClick: (() -> Unit)
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }
    )
}