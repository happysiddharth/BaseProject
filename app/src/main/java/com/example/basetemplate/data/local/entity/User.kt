package com.example.basetemplate.data.local.entity

import androidx.room.*
import java.util.*

@Entity(tableName = "users",
        foreignKeys = [
                ForeignKey(
                        entity = Address::class,
                        parentColumns = ["id"],
                        childColumns = ["address_id"],
                        onDelete = ForeignKey.CASCADE)
        ])
data class User(

        @PrimaryKey(autoGenerate = true)
        var id: Long = 0,

        @ColumnInfo(name = "name")
        var name: String,

        @ColumnInfo(name = "date_of_birth")
        var dateOfBirth: Date,

        @ColumnInfo(name = "address_id")
        var addressId: Long,

        @Ignore
        var selected: Boolean = false
) {
        constructor() : this(0, "", Date(), 0, false)
}