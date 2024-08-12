package com.prasad.data.repository

import com.prasad.domain.common.Result
import com.prasad.domain.entities.CartoonEntity
import com.prasad.domain.entities.CartoonListEntity
import com.prasad.domain.repository.CartoonRepository
import com.prasad.network.Utils
import com.prasad.network.services.CartoonService
import javax.inject.Inject

class CartoonRepositoryImpl @Inject constructor(
    private val cartoonService: CartoonService,
    private val utils: Utils
) : CartoonRepository {
    override suspend fun getCartoonList(): Result<CartoonListEntity> {
        return utils.safeApiCall(
            { cartoonService.getCartoonList() },
            { CartoonListEntity(map { it.toCartoonEntity() }) })
    }

    override suspend fun getCartoonDetails(id: Int): Result<CartoonEntity> {
        return utils.safeApiCall({ cartoonService.getCartoonDetails(id) }, { toCartoonEntity() })
    }
}
