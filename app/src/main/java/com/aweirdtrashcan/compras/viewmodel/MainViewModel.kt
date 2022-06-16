package com.aweirdtrashcan.compras.viewmodel

import androidx.lifecycle.*
import com.aweirdtrashcan.compras.repository.CompraRepository
import com.aweirdtrashcan.compras.roomdb.Compra
import kotlinx.coroutines.launch

class MainViewModel(private val repository: CompraRepository) : ViewModel() {

    val allCompras : LiveData<List<Compra>> = repository.allCompras.asLiveData()

    fun insert(compra: Compra){
        viewModelScope.launch {
            repository.insert(compra)
        }
    }

    fun delete(id: Int) = viewModelScope.launch {
        repository.deleteById(id)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
}