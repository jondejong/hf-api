package com.jondejong.hf.api.integration

import org.junit.AfterClass
import org.junit.BeforeClass

open class BaseOpenIntegrationTest : BaseIntegrationTest() {

    companion object {
        @BeforeClass
        @JvmStatic
        fun setup() {
            app.start()
        }

        @AfterClass
        @JvmStatic
        fun teardown() {
            app.stop()
        }
    }
}