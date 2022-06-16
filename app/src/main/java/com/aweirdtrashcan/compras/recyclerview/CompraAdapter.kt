package com.aweirdtrashcan.compras.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aweirdtrashcan.compras.R
import com.aweirdtrashcan.compras.databinding.RecyclerviewComprasBinding
import com.aweirdtrashcan.compras.roomdb.Compra

class CompraAdapter(
    private var compras : List<Compra>,
    private val onItemSetClick: onItemSetClick
) : RecyclerView.Adapter<CompraAdapter.CompraViewHolder>() {

    private var binding : RecyclerviewComprasBinding? = null

    class CompraViewHolder(
        binding : RecyclerviewComprasBinding,
        val onItemSetClick: onItemSetClick,
        ) : RecyclerView.ViewHolder(binding.root) {
        val tvCompras = binding.tvCompras
        val btnDelete = binding.btnDelete
        fun bind(text : String, id : Int){
            tvCompras.text = text
            btnDelete.setOnClickListener {
                onItemSetClick.itemOnClick(id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompraViewHolder {
        return CompraViewHolder(RecyclerviewComprasBinding.inflate(LayoutInflater.from(parent.context), parent, false), onItemSetClick)
    }

    override fun onBindViewHolder(holder: CompraViewHolder, position: Int) {
        holder.bind(compras[position].compra, compras[position].id)
    }

    override fun getItemCount(): Int {
        return compras.size
    }
}