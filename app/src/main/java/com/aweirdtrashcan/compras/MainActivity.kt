package com.aweirdtrashcan.compras

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.aweirdtrashcan.compras.databinding.ActivityMainBinding
import com.aweirdtrashcan.compras.recyclerview.CompraAdapter
import com.aweirdtrashcan.compras.recyclerview.RecyclerViewStyle
import com.aweirdtrashcan.compras.recyclerview.onItemSetClick
import com.aweirdtrashcan.compras.repository.ComprasOptions
import com.aweirdtrashcan.compras.roomdb.Compra
import com.aweirdtrashcan.compras.viewmodel.CompraViewModelFactory
import com.aweirdtrashcan.compras.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), onItemSetClick {

    private val viewModel : MainViewModel by viewModels{
        CompraViewModelFactory((application as CompraApplication).repository)
    }

    private lateinit var binding : ActivityMainBinding
    private var selectedMerch = arrayListOf<String>()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.allCompras.observe(this, Observer { compras ->
            binding.rvCompras.adapter = CompraAdapter(compras, this)
        })
        binding.btnAdd.setOnClickListener { addComprasDialog() }
        binding.rvCompras.layoutManager = LinearLayoutManager(this)
        binding.rvCompras.addItemDecoration(
            RecyclerViewStyle(10))
        setUpToolBar()
    }

    private fun setUpToolBar(){
        setSupportActionBar(binding.mainToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.mainToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun addComprasDialog(){
        ComprasOptions.options.forEach { _ ->
            ComprasOptions.optionsBool.add(false)
        }
        AlertDialog.Builder(this).apply {
            setTitle("O que você deseja adicionar?")
            setMultiChoiceItems(ComprasOptions.options, ComprasOptions.optionsBool.toBooleanArray()) { dialogInterface, which, isChecked ->
                if (isChecked){
                    selectedMerch.add(ComprasOptions.options[which])
                } else {
                    selectedMerch.remove(ComprasOptions.options[which])
                }
            }
            setPositiveButton("Feito") { dialogInterface, _ ->
                selectedMerch.forEach {
                    viewModel.insert(
                        Compra(
                            0, it
                        )
                    )
                }
                dialogInterface.dismiss()
            }
            setNegativeButton("Sair") { dialogInterface,  _ ->
                dialogInterface.dismiss()
            }
            show()
        }
    }

    override fun itemOnClick(position: Int) {
        viewModel.delete(position)
    }
}