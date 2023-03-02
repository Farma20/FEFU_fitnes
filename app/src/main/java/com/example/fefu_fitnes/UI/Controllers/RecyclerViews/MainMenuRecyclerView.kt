package com.example.fefu_fitnes.UI.Controllers.RecyclerViews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fefu_fitnes.R
import com.example.fefu_fitnes.UI.Models.NewsDataModel

class MainMenuRecyclerView(val inflater: LayoutInflater, val recyclerView: RecyclerView) {

    fun onStart(events:List<NewsDataModel>){
        recyclerView.adapter = RecyclerAdapter(events)
    }

    private inner class RecyclerAdapter(var events:List<NewsDataModel>):
        RecyclerView.Adapter<RecyclerViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
            val view =  inflater.inflate(R.layout.item_recycler_view, parent, false)
            return RecyclerViewHolder(view)
        }

        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            val event = events[position]
            holder.apply {
                text1.text = event.textEvent
            }
        }

        override fun getItemCount(): Int = events.count()

    }

    private inner class RecyclerViewHolder(view: View): RecyclerView.ViewHolder(view){

        val text1: TextView = itemView.findViewById(R.id.event_text)

    }
}