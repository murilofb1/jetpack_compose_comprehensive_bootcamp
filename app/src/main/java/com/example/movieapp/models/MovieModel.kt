package com.example.movieapp.models

import com.example.movieapp.R
import java.util.UUID

data class MovieModel(
    private val id: UUID = UUID.randomUUID(),
    private val name: String,
    private val imageID: Int = R.drawable.kingsman
) {
    fun getId() = id.toString()
    fun getName() = name
    fun getImageId() = imageID

    companion object {
        val defaultList = listOf(
            MovieModel(name = "Avatar"),
            MovieModel(name = "Kingsman"),
            MovieModel(name = "300"),
            MovieModel(name = "Os Farofeiros"),
            MovieModel(name = "Operação Big Hero"),
            MovieModel(name = "Star Wars"),
            MovieModel(name = "O Senhor do Anéis"),
            MovieModel(name = "Avatar")
        )

        fun getItemById(id: String) = defaultList.find { it.getId() == id }


    }
}