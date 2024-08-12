package com.prasad.domain.usecases

import com.prasad.domain.common.Result
import com.prasad.domain.entities.CartoonEntity
import com.prasad.domain.entities.CartoonListEntity
import com.prasad.domain.repository.CartoonRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class CartoonListUseCaseTest {

    private val cartoonRepositoryMock: CartoonRepository = mockk()

    @Test
    fun `GIVEN nothing when CartoonDetailsUseCase on Success returns Result Success`() = runTest {
        val useCase = CartoonListUseCase(cartoonRepositoryMock)
        val cartoonListEntity = getCartoonListEntity()

        coEvery { cartoonRepositoryMock.getCartoonList() } returns Result.Success(cartoonListEntity)

        val result = useCase()

        expectThat(result).isEqualTo(Result.Success(cartoonListEntity))
    }

    @Test
    fun `GIVEN nothing when CartoonDetailsUseCase on Failure returns Result Error`() = runTest {
        val useCase = CartoonListUseCase(cartoonRepositoryMock)

        coEvery { cartoonRepositoryMock.getCartoonList() } returns Result.Error(
            ERROR_CODE,
            ERROR_MESSAGE
        )

        val result = useCase()

        expectThat(result).isEqualTo(Result.Error(ERROR_CODE, ERROR_MESSAGE))
    }

    private fun getCartoonListEntity() = CartoonListEntity(buildList {
        add(getCartoonEntity())
    })

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