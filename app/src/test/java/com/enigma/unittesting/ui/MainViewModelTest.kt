package com.enigma.unittesting.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.enigma.unittesting.data.model.Customer
import com.enigma.unittesting.repositories.CustomerRepository
import com.enigma.unittesting.utils.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class MainViewModelTest {

    private val customerDummy = Customer(1, "Rifqi", "Jakarta")

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var mainViewModel: MainViewModel

    @Mock
    lateinit var customerRepository: CustomerRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        mainViewModel = MainViewModel(customerRepository)
    }

    @Test
    fun mainViewModel_registerNewCustomer() = runBlocking {
        `when`(customerRepository.registerNewCustomer(customerDummy)).thenAnswer { Unit }
        mainViewModel.addCustomer(customerDummy)
        verify(customerRepository, times(1)).registerNewCustomer(customerDummy)
    }

    @Test
    fun mainViewModel_getCustomerById() = runBlocking {
        `when`(customerRepository.getCustomer(1)).thenReturn(customerDummy)
        mainViewModel.getCustomerById(1)
        val actual = mainViewModel.customerLiveData.getOrAwaitValue()
        assertThat(actual.uid).isEqualTo(customerDummy.uid)
    }
}