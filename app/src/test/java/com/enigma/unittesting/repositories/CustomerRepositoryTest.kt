package com.enigma.unittesting.repositories

import com.enigma.unittesting.data.dao.CustomerDao
import com.enigma.unittesting.data.model.Customer
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class CustomerRepositoryTest {
    private val dummyCustomer = Customer(1, "Rifqi", "Jakarta")

    @Mock
    lateinit var dao: CustomerDao

    lateinit var customerRepository: CustomerRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        customerRepository = CustomerRepository(dao)
    }

    @Test
    fun customerRepo_registerNew() = runBlockingTest {
        customerRepository.registerNewCustomer(dummyCustomer)
        verify(dao, times(1)).insert(dummyCustomer)
    }

    @Test
    fun customerRepo_getById() = runBlockingTest {
        `when`(customerRepository.getCustomer(dummyCustomer.uid)).thenReturn(dummyCustomer)
        val customer = customerRepository.getCustomer(1)
        assertThat(customer.uid).isEqualTo(dummyCustomer.uid)
    }

    @Test
    fun customerRepo_getAll() = runBlockingTest {
        `when`(customerRepository.getCustomer()).thenReturn(listOf(
            dummyCustomer,
            dummyCustomer.copy(2),
            dummyCustomer.copy(3)
        ))
        val all = customerRepository.getCustomer()
        assertThat(all).hasSize(3)
    }

    @Test
    fun customerRepo_deleteAll() = runBlockingTest {
        customerRepository.deleteCustomer(dummyCustomer)
        verify(dao, times(1)).delete(dummyCustomer)
    }
}