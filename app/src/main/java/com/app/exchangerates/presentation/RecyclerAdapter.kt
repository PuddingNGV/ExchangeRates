package com.app.exchangerates.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.exchangerates.R
import com.app.exchangerates.domain.models.Currency

class RecyclerAdapter(private val dataSet: List<Currency>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewCharCode: TextView
        val textNominal: TextView
        val textName: TextView
        val textValue: TextView

        init {
            textViewCharCode = view.findViewById(R.id.textCharCode)
            textNominal = view.findViewById(R.id.textNominal)
            textName = view.findViewById(R.id.textName)
            textValue = view.findViewById(R.id.textValue)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recycler_view_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textViewCharCode.text = dataSet[position].charCode
        viewHolder.textNominal.text = dataSet[position].nominal.toString()
        viewHolder.textName.text = dataSet[position].name
        viewHolder.textValue.text = dataSet[position].value.toString()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.lastIndex

}