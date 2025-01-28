package com.nasagallery.view.activity

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.gson.Gson
import com.nasagallery.R
import com.nasagallery.model.local.NASAPhotoItem
import com.nasagallery.viewmodel.GalleryViewModel


@Composable
fun GalleryScreen(
    navController: NavHostController,
    viewModel: GalleryViewModel
) {
    val nasaPhotos by viewModel.photo.collectAsState(initial = null) // Collect state from ViewModel
    val error by viewModel.error.collectAsState(initial = null) // Collect state from ViewModel
    val isDarkTheme by viewModel.isDarkTheme.collectAsState(initial = null)

    LaunchedEffect(Unit) {
        viewModel.fetchPhoto() // Trigger data fetch when the composable is created
    }

    Scaffold(
        topBar = { NasaToolbar(isDarkTheme ?: false){
            viewModel.toggleTheme()
        } },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                if (nasaPhotos == null) {
                    // Show loading state
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                } else if (error?.isNotEmpty() == true) {
                    Text(
                        text = error!!,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                } else {
                    // Display the list of items
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(nasaPhotos!!) { item -> // Iterate over the list

                            NasaItemCard(item = item) {
                                val jsonItem = Gson().toJson(item) // Serialize item to JSON
                                val encodedJson =
                                    Uri.encode(jsonItem) // Encode JSON to avoid special character issues
                                navController.navigate("details/$encodedJson") // Pass as part of the route
                            }
                        }
                    }
                }
            }
        }
    )
}


@Composable
fun NasaItemCard(
    item: NASAPhotoItem,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        onClick = { onClick() } // Clickable card
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // AsyncImage with corner radius and placeholder
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.url)
                    .crossfade(true)
                    .placeholder(R.drawable.placeholder) // Set placeholder image
                    .error(R.drawable.placeholder) // Set fallback if loading fails
                    .build(),
                contentDescription = "NASA Item Image",
                modifier = Modifier
                    .width(150.dp)
                    .height(120.dp)
                    .clip(RoundedCornerShape(12.dp)) // Add corner radius
                    .padding(end = 16.dp),
                contentScale = ContentScale.Crop // Crop the image to fit
            )
            // Text content
            Column(
                modifier = Modifier.weight(1f) // Take all available space
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = 15.sp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = item.date,
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Gray
                )
            }

            // Right Arrow
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Arrow Forward",
                modifier = Modifier
                    .size(24.dp),
                tint = Color.Gray
            )
        }
    }
}


@Composable
private fun NasaToolbar(isDarkTheme: Boolean, toggleTheme: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "NASA Images",
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 24.sp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        toggleTheme()
                    },
                painter = painterResource(
                    if (isDarkTheme) R.drawable.sun else R.drawable.moon
                ),
                contentDescription = "Theme Button"
            )
        }
    }
}

