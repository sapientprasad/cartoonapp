package com.prasad.ui.features.listscreen

import com.prasad.ui.base.SideEffect
import com.prasad.ui.base.ViewIntent
import com.prasad.ui.base.ViewState
import com.prasad.ui.models.CartoonList

data class NavigateToCartoonDetailsSideEffect(val id: Int): SideEffect

sealed class CartoonListViewIntent : ViewIntent {
    data object LoadData : CartoonListViewIntent()
    class OnItemClicked(val id: Int) : CartoonListViewIntent()
}

sealed class CartoonListViewState : ViewState {
    data object Loading : CartoonListViewState()
    data class Success(val data: CartoonList) : CartoonListViewState()
    data class Error(val errorCode: Int, val errorMessage: String) : CartoonListViewState()
}