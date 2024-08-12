package com.prasad.domain.entities

data class CartoonEntity(
    val id: Int,
    val title: String,
    val year: Int,
    val creator: List<String>,
    val genre: List<String>,
    val image: String,
)
