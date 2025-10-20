package com.example.myapplication.ui.theme.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.library.BookListScreen
import com.example.myapplication.library.ManageScreen
import com.example.myapplication.library.StudentListScreen
import com.example.myapplication.library.dataflow.ConfirmScreen
import com.example.myapplication.library.dataflow.ForgotEmailScreen
import com.example.myapplication.library.dataflow.ForgotViewModel
import com.example.myapplication.library.dataflow.ResetPasswordScreen
import com.example.myapplication.library.dataflow.VerifyCodeScreen

sealed class BottomNavItem(val route: String, val icon: ImageVector, val title: String) {
    object Manage : BottomNavItem(Destinations.MANAGE, Icons.Default.Home, "Quản lý")
    object Books : BottomNavItem(Destinations.BOOKS, Icons.Default.List, "DS Sách")
    object Students : BottomNavItem(Destinations.STUDENTS, Icons.Default.Person, "Sinh viên")
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destinations.MAIN_SCREEN) {
        composable(Destinations.MAIN_SCREEN) {
            MainScreen(
                onNavigateToLibrary = { navController.navigate(Destinations.LIBRARY_ROOT) },
                onNavigateToDataFlow = { navController.navigate(Destinations.FORGOT_EMAIL) }
            )
        }

        libraryGraph(navController)

        dataFlowGraph(navController)
    }
}

private fun NavGraphBuilder.libraryGraph(navController: NavHostController) {
    navigation(startDestination = Destinations.MANAGE, route = Destinations.LIBRARY_ROOT) {
        composable(Destinations.MANAGE) {
            LibraryAppScaffold(navController = navController) { paddingModifier ->
                ManageScreen(modifier = paddingModifier)
            }
        }
        composable(Destinations.BOOKS) {
            LibraryAppScaffold(navController = navController) { paddingModifier ->
                BookListScreen(modifier = paddingModifier)
            }
        }
        composable(Destinations.STUDENTS) {
            LibraryAppScaffold(navController = navController) { paddingModifier ->
                StudentListScreen(modifier = paddingModifier)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
private fun NavGraphBuilder.dataFlowGraph(navController: NavHostController) {
    composable(Destinations.FORGOT_EMAIL) {
        val vm: ForgotViewModel = viewModel()
        DataFlowScaffold(title = "Nav (Data Flow)", onBack = { navController.popBackStack() }) {
            ForgotEmailScreen(viewModel = vm, onNext = { navController.navigate(Destinations.VERIFY_CODE) })
        }
    }
    composable(Destinations.VERIFY_CODE) {
        val vm: ForgotViewModel = viewModel()
        DataFlowScaffold(title = "Verification", onBack = { navController.popBackStack() }) {
            VerifyCodeScreen(viewModel = vm, onNext = { navController.navigate(Destinations.RESET_PASSWORD) })
        }
    }
    composable(Destinations.RESET_PASSWORD) {
        val vm: ForgotViewModel = viewModel()
        DataFlowScaffold(title = "Reset Password", onBack = { navController.popBackStack() }) {
            ResetPasswordScreen(viewModel = vm, onNext = { navController.navigate(Destinations.CONFIRM) })
        }
    }
    composable(Destinations.CONFIRM) {
        val vm: ForgotViewModel = viewModel()
        DataFlowScaffold(title = "Confirm", onBack = { navController.popBackStack() }) {
            ConfirmScreen(viewModel = vm, onDone = { navController.popBackStack(Destinations.MAIN_SCREEN, false) })
        }
    }
}

@Composable
private fun LibraryAppScaffold(navController: NavHostController, content: @Composable (Modifier) -> Unit) {
    val bottomNavItems = listOf(
        BottomNavItem.Manage,
        BottomNavItem.Books,
        BottomNavItem.Students
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                bottomNavItems.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        content(Modifier.padding(innerPadding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DataFlowScaffold(title: String, onBack: () -> Unit, content: @Composable () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            content()
        }
    }
}
