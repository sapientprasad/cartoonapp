package com.prasad.domain.base

import com.prasad.domain.repository.CartoonRepository
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import org.junit.Rule

open class BaseTest {

    val cartoonRepositoryMock: CartoonRepository = mockk()
}