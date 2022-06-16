package com.aweirdtrashcan.compras.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.*

@Entity(tableName = "compras_table")
data class Compra(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var compra : String
)