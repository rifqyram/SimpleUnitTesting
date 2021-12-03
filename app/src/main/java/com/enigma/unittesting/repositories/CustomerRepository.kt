package com.enigma.unittesting.repositories

import com.enigma.unittesting.data.dao.CustomerDao
import com.enigma.unittesting.data.model.Customer

class CustomerRepository(private val customerDao: CustomerDao) {
    suspend fun getCustomer() = customerDao.getAll()
    suspend fun registerNewCustomer(customer: Customer) = customerDao.insert(customer)
    suspend fun getCustomer(id: Int): Customer = customerDao.getCustomerById(id)
    suspend fun deleteCustomer(customer: Customer) = customerDao.delete(customer)
}