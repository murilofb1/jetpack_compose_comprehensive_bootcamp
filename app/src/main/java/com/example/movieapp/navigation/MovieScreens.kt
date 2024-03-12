package com.example.movieapp.navigation

import java.lang.IllegalStateException

enum class MovieScreens {
    HomeScreen, DetailsScreen;

    companion object {
        fun fromRoute(route: String?): MovieScreens {
            if (route == null) return HomeScreen
            return when (route.substringBefore("/")) {
                HomeScreen.name -> HomeScreen
                DetailsScreen.name -> DetailsScreen
                else -> throw IllegalStateException("The Route $route is not valid")
            }
        }
    }
}