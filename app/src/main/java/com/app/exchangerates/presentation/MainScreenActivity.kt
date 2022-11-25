package com.app.exchangerates.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.exchangerates.R
import com.app.exchangerates.databinding.ActivityMainScreenBinding
import com.app.exchangerates.domain.models.Currency
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainScreenBinding

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

        val data = mutableListOf(
            Currency("AUD", 1, "Австралийский доллар", 40.1688),
            Currency("GBP", 1, "Фунт стерлингов Соединенного королевства", 71.8791),
            Currency("USD", 1, "Доллар США",  60.5043),
            Currency("EUR", 1, "Евро", 62.285),
            Currency("INR", 1, "Индийских рупий", 74.3886),
            Currency("CAD", 1, "Канадский доллар", 45.1659),
            Currency("KGS", 100, "Киргизских сомов", 71.66),
            Currency("CNY", 10, "Китайских юаней", 84.3616),
            Currency("NOK", 10, "Норвежских крон", 60.2075),
            Currency("UZS", 10000, "Узбекских сумов", 53.9788),
            Currency("AUD", 1, "Австралийский доллар", 40.1688),
            Currency("GBP", 1, "Фунт стерлингов Соединенного королевства", 71.8791),
            Currency("USD", 1, "Доллар США",  60.5043),
            Currency("EUR", 1, "Евро", 62.285),
            Currency("INR", 1, "Индийских рупий", 74.3886),
            Currency("CAD", 1, "Канадский доллар", 45.1659),
            Currency("KGS", 100, "Киргизских сомов", 71.66),
            Currency("CNY", 10, "Китайских юаней", 84.3616),
            Currency("NOK", 10, "Норвежских крон", 60.2075),
            Currency("UZS", 10000, "Узбекских сумов", 53.9788),
            Currency("KRW", 1000, "Вон Республики Корея", 44.7583)
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = RecyclerAdapter(data)

    }
}