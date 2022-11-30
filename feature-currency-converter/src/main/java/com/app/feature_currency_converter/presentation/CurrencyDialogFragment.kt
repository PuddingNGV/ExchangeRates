package com.app.feature_currency_converter.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.feature_currency_converter.databinding.FragmentCurrencyBinding
import com.app.feature_currency_converter.domain.models.CurrencyModelModule
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyDialogFragment : DialogFragment() {
    private var _binding:FragmentCurrencyBinding? = null
    private val binding get() = _binding!!
    private val vm: CurrencyConverterViewModel by viewModels()
    private lateinit var data:  List<CurrencyModelModule>


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
        vm.currencyConverterTest.observe(this@CurrencyDialogFragment) { listData ->
            listData.data.let {
                if (it != null) {
                    data = it
                    binding.recycler.adapter = RecyclerAdapter(data)
                }
            }
        }
        dialog?.window?.setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT)

        binding.recycler.addItemDecoration(
            DividerItemDecoration(
                context, LinearLayoutManager.VERTICAL
            )
        )
        binding.recycler.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}