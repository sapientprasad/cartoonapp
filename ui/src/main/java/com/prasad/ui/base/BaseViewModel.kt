package com.prasad.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<VS : ViewState, VI : ViewIntent, SE : SideEffect> : ViewModel() {

    private val initialState: VS by lazy {
        initialState()
    }

    protected val state: MutableStateFlow<VS> = MutableStateFlow(initialState)

    val stateFlow: StateFlow<VS>
        get() = state

    protected val sideEffect: MutableSharedFlow<SE> = MutableSharedFlow()

    val sideEffectFlow: SharedFlow<SE>
        get() = sideEffect

    abstract fun initialState(): VS

    abstract fun sendIntent(viewIntent: VI)

    companion object {
        const val ERROR_CODE = -1
        const val ERROR_MESSAGE = "You shouldn't be here!!"
    }
}