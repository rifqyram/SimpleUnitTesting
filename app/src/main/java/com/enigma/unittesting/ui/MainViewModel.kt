package com.enigma.unittesting.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enigma.unittesting.data.model.Customer
import com.enigma.unittesting.repositories.CustomerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val customerRepository: CustomerRepository) : ViewModel() {

    private val _customersLiveData = MutableLiveData<List<Customer>>()
    val customersLiveData: LiveData<List<Customer>>
        get() {
            return _customersLiveData
        }

    private val _customerLiveData = MutableLiveData<Customer>()
    val customerLiveData: LiveData<Customer>
        get() {
            return _customerLiveData
        }

    fun addCustomer(customer: Customer) = viewModelScope.launch(Dispatchers.IO) {
        customerRepository.registerNewCustomer(customer)
    }

    fun deleteCustomer(customer: Customer) = viewModelScope.launch(Dispatchers.IO) {
        customerRepository.deleteCustomer(customer)
    }

    fun getCustomerById(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        _customerLiveData.value = customerRepository.getCustomer(id)
    }

    fun getCustomers() = viewModelScope.launch(Dispatchers.IO) {
        _customersLiveData.value = customerRepository.getCustomer()
    }
}