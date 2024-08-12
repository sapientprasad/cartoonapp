package com.prasad.ui.features.detailsscreens

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.prasad.domain.common.Result
import com.prasad.domain.usecases.CartoonDetailsUseCase
import com.prasad.ui.base.BaseTest
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class CartoonDetailsViewModelTest : BaseTest() {

    private val cartoonDetailsUseCase: CartoonDetailsUseCase = mockk()
    private val savedStateHandleMock: SavedStateHandle = mockk()

    @Before
    fun setUp() {
        coEvery { savedStateHandleMock.get<Int>(CARTOON_ID) } returns ID
    }

    @Test
    fun `GIVEN intent sendIntent WHEN calls cartoonDetailsUseCase on Success returns Success ViewState`() =
        runTest {

            coEvery { cartoonDetailsUseCase(ID) } returns Result.Success(stubs.getCartoonEntity())

            val viewModel =
                CartoonDetailsViewModel(savedStateHandleMock, cartoonDetailsUseCase, dispatcher)

            viewModel.stateFlow.test {
                assertTrue(awaitItem() is CartoonDetailsViewState.Success)
            }
        }

    @Test
    fun `GIVEN intent sendIntent WHEN calls cartoonDetailsUseCase on Failure returns Error ViewState`() =
        runTest {

            coEvery { cartoonDetailsUseCase(ID) } returns Result.Error(ERROR_CODE, ERROR_MESSAGE)

            val viewModel =
                CartoonDetailsViewModel(savedStateHandleMock, cartoonDetailsUseCase, dispatcher)

            viewModel.stateFlow.test {
                assertTrue(awaitItem() is CartoonDetailsViewState.Error)
            }

        }
}