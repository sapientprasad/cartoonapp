package com.prasad.domain.usecases

import com.prasad.domain.repository.CartoonRepository
import javax.inject.Inject

class CartoonDetailsUseCase @Inject constructor(
    private val cartoonRepository: CartoonRepository
) {
    suspend operator fun invoke(id: Int) = cartoonRepository.getCartoonDetails(id)
}