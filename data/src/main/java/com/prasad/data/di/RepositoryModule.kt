package com.prasad.data.di

import com.prasad.data.repository.CartoonRepositoryImpl
import com.prasad.domain.repository.CartoonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(cartoonRepositoryImpl: CartoonRepositoryImpl): CartoonRepository
}