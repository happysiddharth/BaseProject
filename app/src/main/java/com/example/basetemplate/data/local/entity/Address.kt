package com.example.basetemplate.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "addresses")
data class Address(

        @PrimaryKey(autoGenerate = true)
        var id: Long = 0,

        @ColumnInfo(name = "city")
        var city: String,

        @ColumnInfo(name = "country")
        var country: String,

        @ColumnInfo(name = "code")
        var code: Int
) {
        constructor() : this(0, "", "", 0)
}
