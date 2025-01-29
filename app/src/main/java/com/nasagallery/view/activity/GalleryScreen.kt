package com.nasagallery.view.activity

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nasagallery.R

@Preview
@Composable
private fun GalleryScreen() {
    Scaffold(
        topBar = { NasaToolbar() },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding) // Ensures content is below the toolbar
            ) {
                // Your screen content goes here
                Text(text = "Hello, World!", style = MaterialTheme.typography.bodyLarge)
            }
        }
    )

}

@Preview
@Composable
private fun NasaToolbar() {
    Box (
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ){
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text("NASA Gallery")
            Icon(
                modifier = Modifier.size(24.dp).clickable { /* change light and dark theme*/ },
                painter = painterResource(id = R.drawable.sun),
                contentDescription = "Theme Button")
        }
    }
}