package com.notesappify.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.notesappify.ui.screens.AddNotesScreen
import com.notesappify.ui.screens.NotesScreens

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController,
        startDestination = "home"
    ) {
        composable("home") {
            NotesScreens {
                navController.navigate("add")
            }
        }
        composable("add") {
            AddNotesScreen()
        }
    }
}