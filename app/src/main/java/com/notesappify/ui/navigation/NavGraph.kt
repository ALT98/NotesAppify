package com.notesappify.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.notesappify.ui.screens.AddNotesScreen
import com.notesappify.ui.screens.NoteDetailScreen
import com.notesappify.ui.screens.NotesScreens
import com.notesappify.ui.screens.SearchScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController,
        startDestination = "home"
    ) {
        composable("home") {
            NotesScreens(addNotes = {
                navController.navigate("add")
            }, update = {
                navController.navigate("add?id=$it")
            }, searchAction = {
                navController.navigate("search")
            }, open = {
                navController.navigate("details")
            })
        }
        composable(
            "add?id={idNote}",
            arguments = listOf(navArgument("idNote") {
                type = NavType.StringType
                nullable = true
            })
        ) {
            val idNote = it.arguments?.getString("idNote")?.toIntOrNull()
            AddNotesScreen(idNote) {
                navController.navigateUp()
            }
        }
        composable("search") {
            SearchScreen {
                navController.navigateUp()
            }
        }
        composable("details") {
            NoteDetailScreen()
        }
    }
}