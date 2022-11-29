package com.app.feature_currency_converter.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.feature_currency_converter.databinding.FragmentCurrencyBinding
import com.app.feature_currency_converter.domain.models.CurrencyModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyDialogFragment : DialogFragment() {
    private var _binding:FragmentCurrencyBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCurrencyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT)
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
        binding.recycler.adapter = RecyclerAdapter(data)
        binding.recycler.addItemDecoration(
            DividerItemDecoration(
                context, LinearLayoutManager.VERTICAL
            )
        )
        binding.recycler.layoutManager = LinearLayoutManager(context)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}