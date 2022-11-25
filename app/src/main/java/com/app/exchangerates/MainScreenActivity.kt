package com.app.exchangerates

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.exchangerates.databinding.ActivityMainScreenBinding

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
    }
}