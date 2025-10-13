package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.ComponentsListScreen
import com.example.myapplication.ui.ImageScreen
import com.example.myapplication.ui.IntroScreen
import com.example.myapplication.ui.RowLayoutScreen
import com.example.myapplication.ui.ColumnLayoutScreen
import com.example.myapplication.ui.TextDetailScreen
import com.example.myapplication.ui.TextFieldScreen
import com.example.myapplication.ui.PasswordFieldScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "intro") {
        composable("intro") {
            IntroScreen { navController.navigate("components_list") }
        }
        composable("components_list") {
            ComponentsListScreen(navController)
        }
        composable("text_detail") {
            TextDetailScreen()
        }
        composable("images") {
            ImageScreen()
        }
        composable("rowlayout") {
            RowLayoutScreen()
        }
        composable("columnlayout") {
            ColumnLayoutScreen()
        }
        composable("textfield") {
            TextFieldScreen()
        }
        composable("passwordfield") {
            PasswordFieldScreen()
        }
    }
}
