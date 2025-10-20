package com.example.udemydemo.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.udemydemo.library.LibraryHomeScreen
import com.example.udemydemo.library.BookListScreen
import com.example.udemydemo.library.StudentListScreen
import com.example.udemydemo.library.ManageScreen
import com.example.udemydemo.dataflow.*

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
