package com.prasad.ui

import com.prasad.domain.entities.CartoonEntity
import com.prasad.domain.entities.CartoonListEntity
import com.prasad.ui.models.Cartoon
import com.prasad.ui.models.CartoonList

fun CartoonListEntity.toCartoonList() = CartoonList(cartoonListEntity.map { it.toCartoon() })

fun CartoonEntity.toCartoon() = Cartoon(
    id, title, year, creator, genre, image
)