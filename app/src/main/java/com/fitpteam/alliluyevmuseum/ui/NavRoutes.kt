package com.fitpteam.alliluyevmuseum.ui

sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    object Room : NavRoutes("room")
    object Exhibit : NavRoutes("exhibit")
    object About : NavRoutes("about")
}