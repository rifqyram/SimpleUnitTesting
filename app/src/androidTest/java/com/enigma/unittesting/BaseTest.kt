package com.enigma.unittesting

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.enigma.unittesting.data.local.TestDatabase
import org.junit.After
import org.junit.Before

abstract class BaseTest {
    var myDatabase: TestDatabase? = null
    abstract fun isMockDatabaseEnabled(): Boolean

    @Before
    open fun setup() {
        this.configureMock()
    }

    @After
    open fun tearDown() {
        this.stopMock()
    }

    open fun configureMock() {
        if (isMockDatabaseEnabled()) {
            myDatabase = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getInstrumentation().targetContext,
                TestDatabase::class.java
            ).allowMainThreadQueries().build()
        }
    }

    open fun stopMock() {
        if (isMockDatabaseEnabled()) {
            myDatabase?.close()
        }
    }
}