package com.prasad.ui.features.listscreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.prasad.ui.composables.ErrorScreen
import com.prasad.ui.composables.ProgressBar

@Composable
fun CartoonListScreen(onItemClick: (id: Int) -> Unit) {

    val viewModel: CartoonListViewModel = hiltViewModel()

    val state = viewModel.stateFlow.collectAsState()

    when (val currentState = state.value) {
        is CartoonListViewState.Error -> {
            ErrorScreen(errorMessage = currentState.errorMessage)
        }

        CartoonListViewState.Loading -> {
            ProgressBar(modifier = Modifier.fillMaxSize())
        }

        is CartoonListViewState.Success -> {
            CartoonList(cartoonList = currentState.data, onItemClick = {
                viewModel.sendIntent(CartoonListViewIntent.OnItemClicked(it))
            })
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.sideEffectFlow.collect()
        {
            onItemClick(it.id)
        }
    }
}