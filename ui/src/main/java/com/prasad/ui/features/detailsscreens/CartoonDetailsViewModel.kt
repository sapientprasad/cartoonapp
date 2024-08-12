package com.prasad.ui.features.detailsscreens

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.prasad.domain.common.Result
import com.prasad.domain.usecases.CartoonDetailsUseCase
import com.prasad.ui.base.BaseViewModel
import com.prasad.ui.di.IODispatcher
import com.prasad.ui.navigation.NavRoutes
import com.prasad.ui.toCartoon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartoonDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val cartoonDetailsUseCase: CartoonDetailsUseCase,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : BaseViewModel<CartoonDetailsViewState, CartoonDetailsViewIntent, Nothing>() {

    init {
        val id = savedStateHandle.get<Int>(NavRoutes.CARTOON_ID)
        sendIntent(CartoonDetailsViewIntent(id ?: 0))
    }

    override fun initialState() = CartoonDetailsViewState.Loading

    override fun sendIntent(viewIntent: CartoonDetailsViewIntent) {
        try {
            viewModelScope.launch(ioDispatcher) {
                when (val result = cartoonDetailsUseCase(viewIntent.id)) {
                    is Result.Error -> {
                        state.update {
                            CartoonDetailsViewState.Error(result.errorCode, result.errorMessage)
                        }
                    }

                    is Result.Success -> {
                        val cartoon = result.data.toCartoon()
                        state.update {
                            CartoonDetailsViewState.Success(cartoon)
                        }
                    }
                }
            }
        } catch (exception: Exception) {
            state.update {
                CartoonDetailsViewState.Error(ERROR_CODE, ERROR_MESSAGE)
            }
        }

    }
}