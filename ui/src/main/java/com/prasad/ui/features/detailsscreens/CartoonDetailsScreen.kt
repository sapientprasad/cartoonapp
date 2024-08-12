package com.prasad.ui.features.detailsscreens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.prasad.ui.composables.ErrorScreen
import com.prasad.ui.composables.ProgressBar

@Composable
fun CartoonDetailsScreen() {
    val viewModel: CartoonDetailsViewModel = hiltViewModel()

    val state = viewModel.stateFlow.collectAsState()

    when (val currentState = state.value) {
        is CartoonDetailsViewState.Error -> {
            ErrorScreen(errorMessage = currentState.errorMessage)
        }

        CartoonDetailsViewState.Loading -> {
            ProgressBar(modifier = Modifier.fillMaxSize())
        }

        is CartoonDetailsViewState.Success -> {
            CartoonDetailsItem(cartoon = currentState.data)
        }
    }
}