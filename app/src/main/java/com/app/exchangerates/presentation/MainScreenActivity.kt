package com.app.exchangerates.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.exchangerates.R
import com.app.exchangerates.databinding.ActivityMainScreenBinding
import com.app.exchangerates.domain.models.CurrencyModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainScreenBinding
    private val vm: MainScreenViewModel by viewModels()
    private lateinit var currencyModel: List<CurrencyModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        //Binding added
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //fun for imageButton replace
        binding.includeConverter.imageButtonChanger.setOnClickListener {
            val textNameSecond = binding.includeConverter.textNameSecondCurrency.text
            val editTextValSecond = binding.includeConverter.editTextValSecondCurrency.text
            binding.includeConverter.textNameSecondCurrency.text = binding.includeConverter.textNameFirstCurrency.text
            binding.includeConverter.editTextValSecondCurrency.text = binding.includeConverter.editTextValFirstCurrency.text
            binding.includeConverter.textNameFirstCurrency.text = textNameSecond
            binding.includeConverter.editTextValFirstCurrency.text = editTextValSecond
        }

        //val dialog = CurrencyDialogFragment()
        //dialog.show(supportFragmentManager, "settingsDialog")

        vm.currencyLiveData.observe(this@MainScreenActivity) { currency ->
            if (!currency.data.isNullOrEmpty()) {
                currencyModel = currency.data.sortedBy { it.name }
                binding.recyclerView.adapter = RecyclerAdapter(currencyModel)
            }
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}