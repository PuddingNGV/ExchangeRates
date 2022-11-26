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
        /*
        vm.currencyLiveData.observe(this@MainScreenActivity) { currency ->
            if (!currency.data.isNullOrEmpty()) {
                currencyModel = currency.data

                binding.recyclerView.layoutManager = LinearLayoutManager(this)
                binding.recyclerView.adapter = RecyclerAdapter(currencyModel)
            }
        }

         */
        val data = mutableListOf(
            CurrencyModel("AUD", 1, "Австралийский доллар", 40.1688),
            CurrencyModel("GBP", 1, "Фунт стерлингов Соединенного королевства", 71.8791),
            CurrencyModel("USD", 1, "Доллар США",  60.5043),
            CurrencyModel("EUR", 1, "Евро", 62.285),
            CurrencyModel("INR", 1, "Индийских рупий", 74.3886),
            CurrencyModel("CAD", 1, "Канадский доллар", 45.1659),
            CurrencyModel("KGS", 100, "Киргизских сомов", 71.66),
            CurrencyModel("CNY", 10, "Китайских юаней", 84.3616),
            CurrencyModel("NOK", 10, "Норвежских крон", 60.2075),
            CurrencyModel("UZS", 10000, "Узбекских сумов", 53.9788),
            CurrencyModel("AUD", 1, "Австралийский доллар", 40.1688),
            CurrencyModel("GBP", 1, "Фунт стерлингов Соединенного королевства", 71.8791),
            CurrencyModel("USD", 1, "Доллар США",  60.5043),
            CurrencyModel("EUR", 1, "Евро", 62.285),
            CurrencyModel("INR", 1, "Индийских рупий", 74.3886),
            CurrencyModel("CAD", 1, "Канадский доллар", 45.1659),
            CurrencyModel("KGS", 100, "Киргизских сомов", 71.66),
            CurrencyModel("CNY", 10, "Китайских юаней", 84.3616),
            CurrencyModel("NOK", 10, "Норвежских крон", 60.2075),
            CurrencyModel("UZS", 10000, "Узбекских сумов", 53.9788),
            CurrencyModel("KRW", 1000, "Вон Республики Корея", 44.7583)
        )

        vm.test()
        //binding.recyclerView.layoutManager = LinearLayoutManager(this)
        //binding.recyclerView.adapter = RecyclerAdapter(data)

    }
}