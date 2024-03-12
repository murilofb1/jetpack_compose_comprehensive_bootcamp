package com.example.movieapp.viewmodels

import androidx.lifecycle.ViewModel
import com.example.movieapp.models.MovieModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter

class HomeViewModel : ViewModel() {
    /*
        val movieList = listOf(
            MovieModel("Avatar", Icons.Default.PlayArrow),
            MovieModel("Kingsman", Icons.Default.PlayArrow),
            MovieModel("300", Icons.Default.PlayArrow),
            MovieModel("Os Farofeiros", Icons.Default.PlayArrow),
            MovieModel("Operação Big Hero", Icons.Default.PlayArrow),
            MovieModel("Star Wars", Icons.Default.PlayArrow),
            MovieModel("O Senhor do Anéis", Icons.Default.PlayArrow),
            MovieModel("Avatar", Icons.Default.PlayArrow),
        )
        *//*
    private val _moviesList = MutableStateFlow(
        listOf(
            MovieModel(name = "Avatar"),
            MovieModel(name = "Kingsman"),
            MovieModel(name = "300"),
            MovieModel(name = "Os Farofeiros"),
            MovieModel(name = "Operação Big Hero"),
            MovieModel(name = "Star Wars"),
            MovieModel(name = "O Senhor do Anéis"),
            MovieModel(name = "Avatar")
        )
    )
    val moviesList = _moviesList.asStateF|low()*/

    fun getItemById(id: String): MovieModel? {
        var movie: MovieModel? = null
        for (item in MovieModel.defaultList) {
            if (item.getId() == id) {
                movie = item
                break
            }
        }
        return movie
    }
}