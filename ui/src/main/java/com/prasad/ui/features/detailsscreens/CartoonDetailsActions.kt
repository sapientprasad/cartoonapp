package com.prasad.ui.features.detailsscreens

import com.prasad.ui.base.ViewIntent
import com.prasad.ui.base.ViewState
import com.prasad.ui.models.Cartoon

class CartoonDetailsViewIntent(val id: Int) : ViewIntent

sealed class CartoonDetailsViewState : ViewState {

    data object Loading : CartoonDetailsViewState()

    class Success(val data: Cartoon) : CartoonDetailsViewState()

    class Error(val errorCode: Int, val errorMessage: String) : CartoonDetailsViewState()
}