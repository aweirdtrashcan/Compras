package com.aweirdtrashcan.compras.repository

import androidx.annotation.WorkerThread
import com.aweirdtrashcan.compras.roomdb.Compra
import com.aweirdtrashcan.compras.roomdb.CompraDao
import kotlinx.coroutines.flow.Flow

class CompraRepository(private val compraDao: CompraDao) {

    val allCompras : Flow<List<Compra>> = compraDao.getAll()

    @WorkerThread
    suspend fun insert(compra: Compra) =
        compraDao.insertCompra(compra)

    suspend fun deleteById(id: Int) =
        compraDao.deleteCompra(id)

    suspend fun deleteAll() =
        compraDao.deleteAll()
}