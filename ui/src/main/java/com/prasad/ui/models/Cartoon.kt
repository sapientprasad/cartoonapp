package com.prasad.ui.models

data class Cartoon(
    val id: Int,
    val title: String,
    val year: Int,
    val creator: List<String>,
    val genre: List<String>,
    val image: String,
)
