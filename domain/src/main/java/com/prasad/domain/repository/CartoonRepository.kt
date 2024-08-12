package com.prasad.domain.repository

import com.prasad.domain.entities.CartoonEntity
import com.prasad.domain.entities.CartoonListEntity
import com.prasad.domain.common.Result

interface CartoonRepository {

    suspend fun getCartoonList(): Result<CartoonListEntity>
    suspend fun getCartoonDetails(id: Int): Result<CartoonEntity>
}