package com.enigma.unittesting.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.enigma.unittesting.data.model.Customer

@Dao
interface CustomerDao {
    @Query("SELECT * FROM customer")
    fun getAll(): List<Customer>

    @Query("SELECT * FROM customer WHERE uid=:id")
    fun getCustomerById(id: Int): Customer

    @Insert
    fun insert(customer: Customer)

    @Delete
    fun delete(customer: Customer)

    @Query("DELETE FROM customer")
    fun deleteAll()
}