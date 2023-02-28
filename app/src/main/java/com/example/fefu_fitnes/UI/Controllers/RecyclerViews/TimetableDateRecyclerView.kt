package com.example.fefu_fitnes.UI.Controllers.RecyclerViews

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fefu_fitnes.R
import com.example.fefu_fitnes.UI.Models.WorkoutDataModel
import com.example.fefu_fitnes.UI.Views.TimetableFragment

class TimetableDateRecyclerView(
    val inflater: LayoutInflater,
    private val dateRecyclerView: RecyclerView){

    private var allRecyclerItems = mutableListOf<View>()

    val myLinearLayoutManager = object :
        LinearLayoutManager(inflater.context, LinearLayoutManager.HORIZONTAL, false) {

        override fun canScrollHorizontally(): Boolean {
            return false
        }
    }


    private val recyclerViewConstants = listOf(
        listOf("пн", "1"), listOf("вт", "2"), listOf("ср", "3"), listOf("чт", "4"),
        listOf("пт", "5"), listOf("сб", "6"), listOf("вс", "7"), listOf("пн", "8"),
        listOf("вт", "9"), listOf("ср", "10"), listOf("чт", "11"), listOf("пт", "12"),
        listOf("сб", "13"), listOf("вс", "14"), listOf("пн", "15"), listOf("вт", "16"),
        listOf("ср", "17"), listOf("чт", "18"), listOf("пт", "19"), listOf("сб", "20"),
        listOf("вс", "21"), listOf("пн", "22"), listOf("вт", "23"), listOf("ср", "24"),
        listOf("чт", "25"), listOf("пт", "26"), listOf("сб", "27"), listOf("вс", "28"),
    )

    fun onStart(){
        dateRecyclerView.layoutManager = myLinearLayoutManager
        dateRecyclerView.adapter = RecyclerViewAdapter(recyclerViewConstants)
    }

    private inner class RecyclerViewAdapter(val data:List<List<String>>):
        RecyclerView.Adapter<RecyclerViewHolder>(){
        @SuppressLint("SetTextI18n")
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
            val view = inflater.inflate(R.layout.item_day_timetable_unchecked, parent, false)
            allRecyclerItems.add(view)
            view.setOnClickListener{
                for (item in allRecyclerItems){
                    if (item != it)
                        item.setBackgroundResource(R.drawable.timetable_item_day_unchecked)
                }

                it.setBackgroundResource(R.drawable.timetable_item_day_checked)
            }
            return RecyclerViewHolder(view)
        }


        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            val item = data[position]

            if (position == 0)
                holder.parent.setBackgroundResource(R.drawable.timetable_item_day_checked)

            holder.apply {
                textDay.text = item[0]
                textNumber.text = item[1]
            }
        }

        override fun getItemCount(): Int {
            return data.count()
        }

    }

    private inner class RecyclerViewHolder(view: View):RecyclerView.ViewHolder(view){
        var parent: View = itemView.findViewById(R.id.parent)
        val textDay: TextView = itemView.findViewById(R.id.day_name)
        val textNumber: TextView = itemView.findViewById(R.id.day_number)
    }
}