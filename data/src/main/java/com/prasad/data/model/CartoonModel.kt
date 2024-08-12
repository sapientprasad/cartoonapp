package com.prasad.data.model

import com.prasad.domain.entities.CartoonEntity

data class CartoonModel(
    val id: Int,
    val title: String,
    val year: Int,
    val creator: List<String>,
    val rating: String,
    val genre: List<String>,
    val runTime: Int,
    val episodes: Int,
    val image: String,
) {
    fun toCartoonEntity(): CartoonEntity {
        return CartoonEntity(id, title, year, creator, genre, image)
    }
}
