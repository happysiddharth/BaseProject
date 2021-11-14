package com.example.basetemplate.data.local.dao

import androidx.room.*
import com.example.basetemplate.data.local.entity.Address
import io.reactivex.Single


@Dao
interface AddressDao {

    @Delete
    fun delete(address: Address): Single<Int> //emits an int value, indicating the number of rows removed from the database.

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMany(vararg addresses: Address): Single<List<Long>> // emits the list of ids added to the database.

    @Query("SELECT * FROM addresses")
    fun getAllAddresses(): Single<List<Address>>
}