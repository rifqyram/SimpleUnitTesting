package com.enigma.unittesting.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.enigma.unittesting.data.dao.CustomerDao
import com.enigma.unittesting.data.model.Customer

@Database(entities = [Customer::class], version = 1)
abstract class TestDatabase : RoomDatabase() {
    abstract fun customerDao(): CustomerDao

    companion object {
        @Volatile
        private var INSTANCE: TestDatabase? = null

        fun getDatabase(context: Context): TestDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TestDatabase::class.java,
                    "simple_room",
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}