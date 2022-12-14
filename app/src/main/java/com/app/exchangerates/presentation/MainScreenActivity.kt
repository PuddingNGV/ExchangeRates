package com.app.exchangerates.presentation

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.exchangerates.R
import com.app.exchangerates.databinding.ActivityMainScreenBinding
import com.app.exchangerates.domain.models.CurrencyModelApp
import com.app.exchangerates.presentation.services.Actions
import com.app.exchangerates.presentation.services.ServiceState
import com.app.exchangerates.presentation.services.ServicesUpdateData
import com.app.exchangerates.presentation.services.getServiceState
import dagger.hilt.android.AndroidEntryPoint
import com.app.feature_currency_converter.presentation.CurrencyDialogFragment

private const val SHARED_PREFS = "shared_prefs_settings"
private const val KEY_CHAR_CODE = "char_code_settings"
private const val KEY_NOMINAL = "nominal_settings"
private const val KEY_NAME = "name_settings"
private const val KEY_VALUE = "value_settings"

@AndroidEntryPoint
class MainScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainScreenBinding
    private val vm: MainScreenViewModel by viewModels()
    private lateinit var currencyModel: List<CurrencyModelApp>

    private lateinit var preferencesCurrency: SharedPreferences
    private lateinit var preferencesListenerFirst: SharedPreferences.OnSharedPreferenceChangeListener
    private lateinit var preferencesListenerSecond: SharedPreferences.OnSharedPreferenceChangeListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        actionOnService(Actions.START)

        preferencesCurrency = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)

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

        binding.includeConverter.textNameFirstCurrency.setOnClickListener {
            val dialog = CurrencyDialogFragment()
            dialog.show(supportFragmentManager, "CurrencyDialog")

            if(this::preferencesListenerSecond.isInitialized) {
                preferencesCurrency.unregisterOnSharedPreferenceChangeListener(preferencesListenerSecond)
            }

            preferencesListenerFirst = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
                if (key == KEY_CHAR_CODE) {
                    binding.includeConverter.textNameFirstCurrency.text = preferencesCurrency.getString(key, "not found")
                    println(preferencesCurrency.getString(key, "not found"))
                }
            }
            preferencesCurrency.registerOnSharedPreferenceChangeListener(preferencesListenerFirst)

        }

        binding.includeConverter.textNameSecondCurrency.setOnClickListener {
            val dialog = CurrencyDialogFragment()
            dialog.show(supportFragmentManager, "CurrencyDialog")

            if(this::preferencesListenerFirst.isInitialized) {
                preferencesCurrency.unregisterOnSharedPreferenceChangeListener(preferencesListenerFirst)
            }

            preferencesListenerSecond = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
                if (key == KEY_CHAR_CODE) {
                    binding.includeConverter.textNameSecondCurrency.text = preferencesCurrency.getString(key, "not found")
                    println(preferencesCurrency.getString(key, "not found"))
                }
            }
            preferencesCurrency.registerOnSharedPreferenceChangeListener(preferencesListenerSecond)
        }

        vm.currencyLiveData.observe(this@MainScreenActivity) { currency ->
            if (!currency.data.isNullOrEmpty()) {
                currencyModel = currency.data.sortedBy { it.name }
                binding.recyclerView.adapter = RecyclerAdapter(currencyModel)
                binding.includeConverter.editTextValFirstCurrency.setText("1")
                binding.includeConverter.editTextValSecondCurrency.setText("1")
                binding.includeConverter.textNameSecondCurrency.text = currencyModel[11].charCode
            }
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        binding.includeConverter.buttonConverter.setOnClickListener {
            val charCodeFirst = binding.includeConverter.textNameFirstCurrency.text.toString()
            val sumValue = binding.includeConverter.editTextValFirstCurrency.text.toString().toDouble()
            val charCodeSecond = binding.includeConverter.textNameSecondCurrency.text.toString()
            val result = vm.convertCurrency(currencyModel, charCodeFirst, sumValue, charCodeSecond )
            binding.includeConverter.editTextValSecondCurrency.setText(result.toString())
        }

    }

    override fun onPause() {
        if (this::preferencesListenerFirst.isInitialized){
            preferencesCurrency.unregisterOnSharedPreferenceChangeListener(preferencesListenerFirst)
        }
        if (this::preferencesListenerSecond.isInitialized){
            preferencesCurrency.unregisterOnSharedPreferenceChangeListener(preferencesListenerSecond)
        }
        super.onPause()
    }

    private fun actionOnService(action: Actions) {
        if (getServiceState(this) == ServiceState.STOPPED && action == Actions.STOP) return
        Intent(this, ServicesUpdateData::class.java).also {
            it.action = action.name
            startForegroundService(it)
        }
    }
}