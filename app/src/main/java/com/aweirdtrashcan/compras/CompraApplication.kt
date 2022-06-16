package com.aweirdtrashcan.compras

import android.app.Application
import com.aweirdtrashcan.compras.repository.CompraRepository
import com.aweirdtrashcan.compras.roomdb.CompraDao
import com.aweirdtrashcan.compras.roomdb.CompraDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class CompraApplication : Application() {

    val database by lazy { CompraDatabase.getDatabase(this) }
    val repository by lazy { CompraRepository(database.compraDao()) }

}