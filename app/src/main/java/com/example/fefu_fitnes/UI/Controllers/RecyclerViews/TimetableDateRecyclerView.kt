package com.example.fefu_fitnes.UI.Controllers.RecyclerViews

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fefu_fitnes.R
import com.example.fefu_fitnes.UI.Models.WorkoutDataModel
import com.example.fefu_fitnes.UI.Views.TimetableFragment

class TimetableDateRecyclerView(
    val inflater: LayoutInflater,
    private val dateRecyclerView: RecyclerView){

    private var currentData = MutableLiveData<Int>().apply {
        value = 3
    }
    private var allRecyclerItems = mutableListOf<View>()


    val myLinearLayoutManager = object :
        LinearLayoutManager(inflater.context, LinearLayoutManager.HORIZONTAL, false) {

        override fun canScrollHorizontally(): Boolean {
            return false
        }
    }

    private val recyclerViewConstants = listOf(
        mutableListOf("пн", "27", "false"), mutableListOf("вт", "28", "false"), mutableListOf("ср", "1", "false"), mutableListOf("чт", "2", "false"),
        mutableListOf("пт", "3", "true"), mutableListOf("сб", "4", "false"), mutableListOf("вс", "5", "false"), mutableListOf("пн", "6", "false"),
        mutableListOf("вт", "7", "false"), mutableListOf("ср", "8", "false"), mutableListOf("чт", "9", "false"), mutableListOf("пт", "10", "false"),
        mutableListOf("сб", "11", "false"), mutableListOf("вс", "12", "false"), mutableListOf("пн", "13", "false"), mutableListOf("вт", "14", "false"),
        mutableListOf("ср", "15", "false"), mutableListOf("чт", "16", "false"), mutableListOf("пт", "17", "false"), mutableListOf("сб", "18", "false"),
        mutableListOf("вс", "19", "false"), mutableListOf("пн", "20", "false"), mutableListOf("вт", "21", "false"), mutableListOf("ср", "22", "false"),
        mutableListOf("чт", "23", "false"), mutableListOf("пт", "24", "false"), mutableListOf("сб", "25", "false"), mutableListOf("вс", "26", "false"),
    )

    fun onStart(){
        dateRecyclerView.layoutManager = myLinearLayoutManager
        dateRecyclerView.adapter = RecyclerViewAdapter(recyclerViewConstants)
    }

    fun getCurrentData(): LiveData<Int> {
        return currentData
    }

    private inner class RecyclerViewAdapter(val data:List<MutableList<String>>):
        RecyclerView.Adapter<RecyclerViewHolder>(){
        @SuppressLint("SetTextI18n")
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
            val view = inflater.inflate(R.layout.item_day_timetable_unchecked, parent, false)

            return RecyclerViewHolder(view)
        }



        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
//            if (position == 4)
//                holder.parent.setBackgroundResource(R.drawable.timetable_item_day_checked)
            allRecyclerItems.add(holder.parent)
            holder.parent.setBackgroundResource(R.drawable.timetable_item_day_unchecked)
            if(data[position][2] == "true"){
                holder.parent.setBackgroundResource(R.drawable.timetable_item_day_checked)
            }
            holder.parent.setOnClickListener{
                for(item in allRecyclerItems){
                    item.setBackgroundResource(R.drawable.timetable_item_day_unchecked)
                }

                data[position][2] = "true"
                currentData.value = data[position][1].toInt()

                for (pos in data.indices){
                    if(data[pos] !=  data[position]){
                        data[pos][2] = "false"
                    }
                }

                if(data[position][2] == "true"){
                    holder.parent.setBackgroundResource(R.drawable.timetable_item_day_checked)
                }
                else{
                    holder.parent.setBackgroundResource(R.drawable.timetable_item_day_unchecked)
                }
            }


            holder.apply {
                textDay.text = data[position][0]
                textNumber.text = data[position][1]
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