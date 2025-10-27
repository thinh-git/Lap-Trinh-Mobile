package com.example.myapplication.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.screens.BookListScreen
import com.example.myapplication.ui.screens.ConfirmScreen
import com.example.myapplication.ui.screens.ForgotEmailScreen
import com.example.myapplication.ui.screens.ForgotViewModel
import com.example.myapplication.ui.screens.LibraryHomeScreen
import com.example.myapplication.ui.screens.ManageScreen
import com.example.myapplication.ui.screens.ResetPasswordScreen
import com.example.myapplication.ui.screens.StudentListScreen
import com.example.myapplication.ui.screens.VerifyCodeScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destinations.LIBRARY_HOME) {
        // Library flow
        composable(Destinations.LIBRARY_HOME) {
            LibraryHomeScreen(
                onOpenBooks = { navController.navigate(Destinations.BOOKS) },
                onOpenStudents = { navController.navigate(Destinations.STUDENTS) },
                onOpenManage = { navController.navigate(Destinations.MANAGE) }
            )
        }
        composable(Destinations.BOOKS) {
            BookListScreen(onBack = { navController.popBackStack() })
        }
        composable(Destinations.STUDENTS) {
            StudentListScreen(onBack = { navController.popBackStack() })
        }
        composable(Destinations.MANAGE) {
            ManageScreen(onBack = { navController.popBackStack() })
        }

        // Dataflow: forgot password sample flow
        composable(Destinations.FORGOT_EMAIL) {
            val vm: ForgotViewModel = viewModel()
            ForgotEmailScreen(
                viewModel = vm,
                onNext = { navController.navigate(Destinations.VERIFY_CODE) }
            )
        }
        composable(Destinations.VERIFY_CODE) {
            val vm: ForgotViewModel = viewModel()
            VerifyCodeScreen(viewModel = vm, onNext = { navController.navigate(Destinations.RESET_PASSWORD) })
        }
        composable(Destinations.RESET_PASSWORD) {
            val vm: ForgotViewModel = viewModel()
            ResetPasswordScreen(viewModel = vm, onNext = { navController.navigate(Destinations.CONFIRM) })
        }
        composable(Destinations.CONFIRM) {
            val vm: ForgotViewModel = viewModel()
            ConfirmScreen(viewModel = vm, onDone = { navController.popBackStack(Destinations.LIBRARY_HOME, false) })
        }
    }
}
