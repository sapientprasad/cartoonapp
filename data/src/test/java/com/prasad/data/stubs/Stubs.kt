package com.prasad.data.stubs

import com.prasad.data.model.CartoonModel

class Stubs {

    fun getCartoonModel() = CartoonModel(
        id = ID,
        title = TITLE,
        year = YEAR,
        creator = CREATOR,
        rating = RATING,
        runTime = RUN_TIME,
        genre = GENRE,
        episodes = EPISODES,
        image = IMAGE
    )

    fun getCartoonListModel() = (buildList {
        add(getCartoonModel())
    })


    private companion object {
        private const val ID = 1
        private const val TITLE = "The Simpsons"
        private const val YEAR = 2024
        private val CREATOR = listOf("Matt Groening")
        private const val RATING = "5"
        private const val RUN_TIME = 30
        private val GENRE = listOf("Comedy")
        private const val EPISODES = 10
        private const val IMAGE =
            "https://m.media-amazon.com/images/M/MV5BYjFkMTlkYWUtZWFhNy00M2FmLThiOTYtYTRiYjVlZWYxNmJkXkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_SY1000_CR0,0,666,1000_AL_.jpg"
    }
}