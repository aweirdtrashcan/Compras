package com.aweirdtrashcan.compras.roomdb

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CompraDao {
    @Query("SELECT * FROM compras_table")
    fun getAll() : Flow<List<Compra>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompra(compra: Compra)

    @Query("DELETE FROM compras_table WHERE id LIKE :id")
    suspend fun deleteCompra(id: Int)

    @Query("DELETE FROM compras_table")
    suspend fun deleteAll()
}