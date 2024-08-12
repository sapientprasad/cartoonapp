package com.prasad.data.repository

import com.prasad.data.stubs.Stubs
import com.prasad.domain.common.Result
import com.prasad.domain.entities.CartoonListEntity
import com.prasad.network.Utils
import com.prasad.network.model.CartoonModel
import com.prasad.network.services.CartoonService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class CartoonRepositoryImplTest {

    private val cartoonServiceMock: CartoonService = mockk()
    private val cartoonModelResponseMock: Response<CartoonModel> = mockk()
    private val cartoonModelListResponseMock: Response<List<CartoonModel>> = mockk()

    private val stubs = Stubs()
    private val utils = Utils()

    private lateinit var cartoonRepositoryImpl: CartoonRepositoryImpl

    @Before
    fun setUp() {
        cartoonRepositoryImpl = CartoonRepositoryImpl(cartoonServiceMock, utils)
    }

    @Test
    fun `GIVEN nothing when getCartoonList on Success returns Result Success`() = runTest {

        val cartoonModelList = stubs.getCartoonListModel()

        coEvery { cartoonModelListResponseMock.isSuccessful } returns true
        coEvery { cartoonModelListResponseMock.body() } returns cartoonModelList
        coEvery { cartoonServiceMock.getCartoonList() } returns cartoonModelListResponseMock

        val cartoonList = cartoonRepositoryImpl.getCartoonList()
        expectThat(cartoonList)
            .isEqualTo(Result.Success(CartoonListEntity(cartoonModelList.map { it.toCartoonEntity() })))

    }

    @Test
    fun `GIVEN nothing when getCartoonList on Failure returns Result Error`() = runTest {

        coEvery { cartoonModelListResponseMock.isSuccessful } returns false
        coEvery { cartoonServiceMock.getCartoonList() } returns cartoonModelListResponseMock

        val result = cartoonRepositoryImpl.getCartoonList()

        expectThat(result).isEqualTo(Result.Error(ERROR_CODE, ERROR_MESSAGE))
    }

    @Test
    fun `GIVEN nothing when getCartoonList on Null body returns Result Error`() = runTest {

        coEvery { cartoonModelListResponseMock.isSuccessful } returns true
        coEvery { cartoonModelListResponseMock.body() } returns null
        coEvery { cartoonServiceMock.getCartoonList() } returns cartoonModelListResponseMock

        val result = cartoonRepositoryImpl.getCartoonList()

        expectThat(result).isEqualTo(Result.Error(ERROR_CODE, ERROR_MESSAGE))
    }

    @Test
    fun `GIVEN nothing when getCartoonList on exception returns Result Error`() = runTest {

        coEvery { cartoonServiceMock.getCartoonList() } throws Exception(ERROR_MESSAGE)

        val result = cartoonRepositoryImpl.getCartoonList()

        expectThat(result).isEqualTo(Result.Error(ERROR_CODE, ERROR_MESSAGE))
    }

    @Test
    fun `GIVEN cartoon Id when getCartoonDetails on Success returns Result Success`() = runTest {

        val cartoonModel = stubs.getCartoonModel()

        coEvery { cartoonModelResponseMock.isSuccessful } returns true
        coEvery { cartoonModelResponseMock.body() } returns cartoonModel
        coEvery { cartoonServiceMock.getCartoonDetails(ID) } returns cartoonModelResponseMock

        val result = cartoonRepositoryImpl.getCartoonDetails(ID)
        expectThat(result).isEqualTo(Result.Success(cartoonModel.toCartoonEntity()))
    }

    @Test
    fun `GIVEN cartoon Id when getCartoonDetails on Failure returns Result Error`() = runTest {

        coEvery { cartoonModelResponseMock.isSuccessful } returns false
        coEvery { cartoonServiceMock.getCartoonDetails(ID) } returns cartoonModelResponseMock

        val result = cartoonRepositoryImpl.getCartoonDetails(ID)

        expectThat(result).isEqualTo(Result.Error(ERROR_CODE, ERROR_MESSAGE))
    }

    @Test
    fun `GIVEN cartoon Id when getCartoonDetails on Null body returns Result Error`() = runTest {

        coEvery { cartoonModelResponseMock.isSuccessful } returns true
        coEvery { cartoonModelResponseMock.body() } returns null
        coEvery { cartoonServiceMock.getCartoonDetails(ID) } returns cartoonModelResponseMock

        val result = cartoonRepositoryImpl.getCartoonDetails(ID)

        expectThat(result).isEqualTo(Result.Error(ERROR_CODE, ERROR_MESSAGE))
    }

    @Test
    fun `GIVEN cartoon Id when getCartoonDetails on Exception returns Result Error`() = runTest {

        coEvery { cartoonServiceMock.getCartoonDetails(ID) } throws Exception(ERROR_MESSAGE)

        val result = cartoonRepositoryImpl.getCartoonDetails(ID)

        expectThat(result).isEqualTo(Result.Error(ERROR_CODE, ERROR_MESSAGE))
    }

    private companion object {
        private const val ERROR_CODE = -1
        private const val ERROR_MESSAGE = "You shouldn't be here!!"
        private const val ID = 1
    }

}