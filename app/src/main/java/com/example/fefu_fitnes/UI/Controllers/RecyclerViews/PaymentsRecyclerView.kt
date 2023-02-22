package com.example.fefu_fitnes.UI.Controllers.RecyclerViews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.fefu_fitnes.R


class PaymentsRecyclerView(val inflater: LayoutInflater, var recyclerView: RecyclerView) {


    fun onStart(){
        recyclerView.adapter = PaymentsRecyclerViewAdapter()
    }


    private inner class PaymentsRecyclerViewAdapter():
        RecyclerView.Adapter<ViewHolder>(){
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ViewHolder {
            return if (viewType == 0){
                val view = inflater.inflate(R.layout.item_payments_aboniment_label, parent, false)
                PaymentsRecyclerLabelViewHolder(view)
            } else{
                val view = inflater.inflate(R.layout.item_payments_aboniment, parent, false)
                PaymentsRecyclerViewHolder(view)
            }

        }

        override fun getItemViewType(position: Int): Int {
            return position
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            return Unit
        }

        override fun getItemCount(): Int {
            return 10
        }
    }



    private class PaymentsRecyclerViewHolder(view: View):ViewHolder(view){
        val subsLabel: TextView = itemView.findViewById(R.id.subscription_label)
        val subsInfo:TextView = itemView.findViewById(R.id.subscription_info)
        val subsPrice:TextView = itemView.findViewById(R.id.subscription_price)
    }

    private class PaymentsRecyclerLabelViewHolder(view:View):ViewHolder(view){
        val paymentLabel: TextView = itemView.findViewById(R.id.aboniment_label)
    }
}