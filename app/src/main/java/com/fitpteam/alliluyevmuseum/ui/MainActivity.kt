package com.fitpteam.alliluyevmuseum.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fitpteam.alliluyevmuseum.ui.screens.ExhibitScreen
import com.fitpteam.alliluyevmuseum.ui.screens.HomeScreen
import com.fitpteam.alliluyevmuseum.ui.screens.RoomScreen
import com.fitpteam.alliluyevmuseum.ui.theme.AlliluyevMuseumTheme
import com.fitpteam.alliluyevmuseum.ui.viewmodels.MainViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AlliluyevMuseumTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val owner = LocalViewModelStoreOwner.current
                    owner?.let {
                        val viewModel: MainViewModel = viewModel(
                            it,
                            "MainViewModel",
                            MainViewModel.Factory
                        )
                        viewModel.uploadList()

                        MainScreen()
                    }
                }
            }
        }
    }

}

@Composable
private fun MainScreen() {
    val navController = rememberNavController()
    val onBackPress: () -> Unit = { navController.navigateUp() }

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route,
    ) {
        composable(NavRoutes.Home.route) {
            HomeScreen { roomId -> navController.navigate(NavRoutes.Room.route + "/$roomId") }
        }

        composable(NavRoutes.Room.route+"/{roomId}") { backStackEntry ->
            val roomId = backStackEntry.arguments?.getString("roomId")
            RoomScreen(
                roomId!!.toInt(),
                { id, name, description, roomId ->  navController.navigate(NavRoutes.Exhibit.route + "/$id/$name/$description/$roomId")},
                onBackPress)
        }

        composable(NavRoutes.Exhibit.route+"/{exhibitId}/{exhibitName}/{exhibitDescription}/{exhibitRoomId}") { backStackEntry ->
            val exhibitId = backStackEntry.arguments?.getString("exhibitId")?.toInt() ?: 0
            val exhibitName = backStackEntry.arguments?.getString("exhibitName")
            val exhibitDescription = backStackEntry.arguments?.getString("exhibitDescription")
            val exhibitRoomId = backStackEntry.arguments?.getString("exhibitRoomId")?.toInt() ?: 0
            ExhibitScreen(exhibitId, exhibitName, exhibitDescription, exhibitRoomId, onBackPress)
        }
    }
}