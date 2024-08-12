package com.prasad.ui.features.listscreen

import androidx.lifecycle.viewModelScope
import com.prasad.domain.common.Result
import com.prasad.domain.usecases.CartoonListUseCase
import com.prasad.ui.base.BaseViewModel
import com.prasad.ui.di.IODispatcher
import com.prasad.ui.toCartoonList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartoonListViewModel @Inject constructor(
    private val cartoonListUseCase: CartoonListUseCase,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : BaseViewModel<CartoonListViewState, CartoonListViewIntent, NavigateToCartoonDetailsSideEffect>() {

    init {
        sendIntent(CartoonListViewIntent.LoadData)
    }

    override fun initialState() = CartoonListViewState.Loading

    override fun sendIntent(viewIntent: CartoonListViewIntent) {
        when (viewIntent) {
            CartoonListViewIntent.LoadData -> getCartoonList()
            is CartoonListViewIntent.OnItemClicked -> {
                viewModelScope.launch(ioDispatcher) {
                    sideEffect.emit(NavigateToCartoonDetailsSideEffect(viewIntent.id))
                }
            }
        }
    }

    private fun getCartoonList() {
        try {
            viewModelScope.launch(ioDispatcher) {
                when (val result = cartoonListUseCase()) {
                    is Result.Error -> {
                        state.update {
                            CartoonListViewState.Error(result.errorCode, result.errorMessage)
                        }
                    }

                    is Result.Success -> {
                        val cartoonList = result.data.toCartoonList()
                        state.update {
                            CartoonListViewState.Success(cartoonList)
                        }
                    }
                }
            }
        } catch (exception: Exception) {
            state.update {
                CartoonListViewState.Error(ERROR_CODE, ERROR_MESSAGE)
            }
        }
    }
}