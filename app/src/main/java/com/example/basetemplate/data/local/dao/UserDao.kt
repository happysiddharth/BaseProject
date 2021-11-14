package com.example.basetemplate.data.local.dao

import androidx.room.*
import com.example.basetemplate.data.local.entity.User
import io.reactivex.Single


@Dao
interface UserDao {

    @Insert
    fun insert(user: User): Single<Long> // emits a long, which is the new rowId for the inserted item

    @Update
    fun update(user: User): Single<Int> // emits an int value, indicating the number of rows updated in the database.

    @Delete
    fun delete(user: User): Single<Int> //emits an int value, indicating the number of rows removed from the database.

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMany(vararg users: User): Single<List<Long>> // emits the list of users ids added to the database.

    @Query("SELECT * FROM users")
    fun getAllUsers(): Single<List<User>>

    @Query("SELECT * from users where id = :id LIMIT 1")
    fun getUserById(id: Long): Single<User>

    @Query("SELECT count(*) from users")
    fun count(): Single<Int>
}