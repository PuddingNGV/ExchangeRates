package com.app.feature_currency_converter.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.feature_currency_converter.R
import com.app.feature_currency_converter.domain.models.CurrencyModelModule

class RecyclerAdapter(
    private val dataSet: List<CurrencyModelModule>,
    private val listener: OnItemClickListener
    ) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

   inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener{
        val textViewCharCode: TextView
        val textName: TextView

        init {
            itemView.setOnClickListener(this)
            textViewCharCode = view.findViewById(R.id.textCharCode)
            textName = view.findViewById(R.id.textName)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recycler_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textViewCharCode.text = dataSet[position].charCode
        viewHolder.textName.text = dataSet[position].name
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.lastIndex

}