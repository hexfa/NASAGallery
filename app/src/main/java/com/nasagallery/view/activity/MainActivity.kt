package com.nasagallery.view.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.nasagallery.model.local.NASAPhotoItem
import com.nasagallery.view.theme.NASAGalleryTheme
import com.nasagallery.viewmodel.GalleryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : ComponentActivity() {
    private val galleryViewModel: GalleryViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDarkTheme by galleryViewModel.isDarkTheme.collectAsState()
            NASAGalleryTheme(useDarkTheme = isDarkTheme) {
                AppNavigation(galleryViewModel)
            }
        }
    }
}


@Composable
fun AppNavigation(galleryViewModel: GalleryViewModel) {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = "gallery") {
        composable("gallery") {
            GalleryScreen(navController = navController, galleryViewModel)
        }

        composable(
            route = "details/{jsonItem}", // Define jsonItem as part of the route
            arguments = listOf(navArgument("jsonItem") { type = NavType.StringType }) // Specify argument type
        ) { backStackEntry ->
            val jsonItem = backStackEntry.arguments?.getString("jsonItem")
            val nasaPhotoItem = Gson().fromJson(jsonItem, NASAPhotoItem::class.java)
            NasaDetailsScreen(navController = navController, nasaPhotoItem = nasaPhotoItem)
        }
    }
}