package com.enigma.unittesting.data.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.enigma.unittesting.BaseTest
import com.enigma.unittesting.data.model.Customer
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CustomerDaoTest : BaseTest() {

    override fun isMockDatabaseEnabled(): Boolean = true

    val customerDummy = Customer(1, "Rifqi", "Jakarta")

    @Test
    fun customerDao_insertRegisterCustomerAndFindById() {
        myDatabase?.run {
            customerDao().insert(customerDummy)
            val customerById = customerDao().getCustomerById(customerDummy.uid)
            assertThat(customerById).isNotNull()
            assertThat(customerById.name).isEqualTo(customerDummy.name)
        }
    }

    @Test
    fun customerDao_getAllCustomer() {
        myDatabase?.run {
            customerDao().insert(customerDummy)
            customerDao().insert(customerDummy.copy(uid = 2))
            customerDao().insert(customerDummy.copy(uid = 3))
            val all = customerDao().getAll()
            assertThat(all).hasSize(3)
        }
    }

    @Test
    fun customerDao_deleteCustomer() {
        myDatabase?.run {
            customerDao().insert(customerDummy)
            customerDao().delete(customerDummy)
            val all = customerDao().getAll()
            assertThat(all).isEmpty()
        }
    }
}