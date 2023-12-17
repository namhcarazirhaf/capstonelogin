package com.belajar.capstoneapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.belajar.capstoneapp.navigation.NavigationItem
import com.belajar.capstoneapp.navigation.Screen
import com.belajar.capstoneapp.ui.screen.camera.CameraScreen
import com.belajar.capstoneapp.ui.screen.detail.DetailScreen
import com.belajar.capstoneapp.ui.screen.diary.DiaryScreen
import com.belajar.capstoneapp.ui.screen.home.HomeScreen
import com.belajar.capstoneapp.ui.screen.login.LoginScreen
import com.belajar.capstoneapp.ui.screen.login.RegisterScreen
import com.belajar.capstoneapp.ui.theme.CapstoneAppTheme

@Composable
fun CapstoneApp() {
    val navController = rememberNavController()
    var isLoggedIn by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            if (isLoggedIn) {
                BottomBar(navController)
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Login.route,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(Screen.Login.route) {
                LoginScreen(
                    navController = navController,
                    onLoginSuccess = { isLoggedIn = true }
                )
            }

            composable(Screen.Register.route) {
                RegisterScreen(
                    navController = navController,
                    onLoginSuccess = { isLoggedIn = true }
                )
            }

            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { foodId ->
                        navController.navigate(Screen.DetailScreen.createRoute(foodId))
                    }
                )
            }

            composable(Screen.Camera.route) {
                CameraScreen(

                )
            }

            composable(Screen.Diary.route) {
                DiaryScreen(
                    navigateToDetail = { foodId ->
                        navController.navigate(Screen.DetailScreen.createRoute(foodId))
                    }
                )
            }

            composable(
                route = Screen.DetailScreen.route,
                arguments = listOf(
                    navArgument("id") { type = NavType.StringType }
                )
            ) {
                val id = it.arguments?.getString("id") ?: "1"
                DetailScreen(
                    foodId = id,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

@Composable
private fun BottomBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        val navigationItems = listOf(
            NavigationItem(
                title = "",
                desc = "Home Screen",
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = "Camera",
                desc = "Camera Screen",
                icon = Icons.Default.AddCircle,
                screen = Screen.Camera
            ),
            NavigationItem(
                title = "Diary",
                desc = "Diary Screen",
                icon = Icons.Default.Favorite,
                screen = Screen.Diary
            )
        )
        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CapstoneAppPreview() {
    CapstoneAppTheme {
        CapstoneApp()
    }
}
