package com.prasad.domain.usecases

import com.prasad.domain.common.Result
import com.prasad.domain.entities.CartoonEntity
import com.prasad.domain.repository.CartoonRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class CartoonDetailsUseCaseTest {

    private val cartoonRepositoryMock: CartoonRepository = mockk()

    @Test
    fun `GIVEN cartoon Id when CartoonDetailsUseCase on Success returns Result Success`() =
        runTest {
            val useCase = CartoonDetailsUseCase(cartoonRepositoryMock)
            val cartoonEntity = getCartoonEntity()

            coEvery { cartoonRepositoryMock.getCartoonDetails(1) } returns Result.Success(
                cartoonEntity
            )

            useCase(1)

            val result = cartoonRepositoryMock.getCartoonDetails(1)

            expectThat(result).isEqualTo(Result.Success(cartoonEntity))
        }

    @Test
    fun `GIVEN cartoon Id when CartoonDetailsUseCase on Failure returns Result Error`() = runTest {
        val useCase = CartoonDetailsUseCase(cartoonRepositoryMock)

        coEvery { cartoonRepositoryMock.getCartoonDetails(1) } returns Result.Error(
            ERROR_CODE,
            ERROR_MESSAGE
        )

        useCase(1)

        val result = cartoonRepositoryMock.getCartoonDetails(1)

        expectThat(result).isEqualTo(Result.Error(ERROR_CODE, ERROR_MESSAGE))
    }

    private companion object {
        private const val ERROR_CODE = -1
        private const val ERROR_MESSAGE = "You shouldn't be here!"

        private const val ID = 1
        private const val TITLE = "The Simpsons"
        private const val YEAR = 2024
        private val CREATOR = listOf("Matt Groening")
        private val GENRE = listOf("Comedy")
        private const val IMAGE =
            "https://m.media-amazon.com/images/M/MV5BYjFkMTlkYWUtZWFhNy00M2FmLThiOTYtYTRiYjVlZWYxNmJkXkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_SY1000_CR0,0,666,1000_AL_.jpg"

        fun getCartoonEntity() = CartoonEntity(
            id = ID,
            title = TITLE,
            year = YEAR,
            creator = CREATOR,
            genre = GENRE,
            image = IMAGE
        )
    }
}