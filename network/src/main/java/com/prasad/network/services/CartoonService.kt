package com.prasad.network.services

import com.prasad.network.model.CartoonModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CartoonService {

    @GET("cartoons2D")
    suspend fun getCartoonList(): Response<List<CartoonModel>>

    @GET("cartoons2D/{id}")
    suspend fun getCartoonDetails(@Path("id") id: Int): Response<CartoonModel>
}