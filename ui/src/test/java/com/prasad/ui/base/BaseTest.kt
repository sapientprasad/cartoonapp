package com.prasad.ui.base

import com.prasad.ui.rules.TestDispatcherRule
import com.prasad.ui.stubs.Stubs
import org.junit.Rule

open class BaseTest {

    @get: Rule
    val dispatcherRule = TestDispatcherRule()

    val dispatcher = dispatcherRule.getDispatcher()

    val stubs = Stubs()

    companion object {
        const val ID = 1
        const val CARTOON_ID = "cartoonId"
        const val ERROR_MESSAGE = "You shouldn't be here!"
        const val ERROR_CODE = -1
    }
}