package com.prasad.ui.features.listscreen

import app.cash.turbine.test
import com.prasad.domain.common.Result
import com.prasad.domain.usecases.CartoonListUseCase
import com.prasad.ui.base.BaseTest
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Test

class CartoonListViewModelTest : BaseTest() {

    private val cartoonListUseCase: CartoonListUseCase = mockk()

    @Test
    fun `GIVEN intent LoadData WHEN calls getCartoonList on Success returns Success ViewState`() = runTest {

        coEvery { cartoonListUseCase() } returns Result.Success(stubs.getCartoonListEntity())

        val viewModel = CartoonListViewModel(cartoonListUseCase, dispatcher)

        viewModel.stateFlow.test {
            assertTrue(awaitItem() is CartoonListViewState.Success)
        }
    }

    @Test
    fun `GIVEN intent LoadData WHEN calls getCartoonList on Failure returns Error ViewState`() = runTest {
        coEvery { cartoonListUseCase() } returns Result.Error(ERROR_CODE, ERROR_MESSAGE)

        val viewModel = CartoonListViewModel(cartoonListUseCase, dispatcher)

        viewModel.stateFlow.test {
            assertTrue(awaitItem() is CartoonListViewState.Error)
        }
    }

    @Test(expected = java.lang.Exception::class)
    fun `GIVEN intent LoadData WHEN calls getCartoonList on Exception returns Error ViewState`() = runTest {
        coEvery { cartoonListUseCase() } throws Exception(ERROR_MESSAGE)

        val viewModel = CartoonListViewModel(cartoonListUseCase, dispatcher)

        viewModel.stateFlow.test {
            assertTrue(awaitItem() is CartoonListViewState.Loading)
        }
    }

    @Test
    fun `GIVEN intent OnItemClicked WHEN calls send intent navigates to CartoonDetailsScreen with id`() = runTest {
        coEvery { cartoonListUseCase() } returns Result.Success(stubs.getCartoonListEntity())

        val cartoonListViewModel =
            CartoonListViewModel(cartoonListUseCase, dispatcher)

        with(cartoonListViewModel) {
            sideEffectFlow.test {
                sendIntent(CartoonListViewIntent.OnItemClicked(id = ID))
                assertEquals(awaitItem().id, ID)
            }
        }
    }
}