package com.aweirdtrashcan.compras.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Compra::class], version = 1)
abstract class CompraDatabase : RoomDatabase() {
    abstract fun compraDao() : CompraDao

    companion object {
        @Volatile
        private var INSTANCE : CompraDatabase? = null

        fun getDatabase(context: Context) : CompraDatabase {
            return INSTANCE?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CompraDatabase::class.java,
                    "compras_table"
                ).build()
                instance
            }
        }
    }
}