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
import java.util.Calendar

class TimetableDateRecyclerView(
    val inflater: LayoutInflater,
    private val dateRecyclerView: RecyclerView){

    private var currentData = MutableLiveData<Int>().apply {
        value = 3
    }
    private var allRecyclerItems = mutableListOf<View>()

    private val calendar:Calendar = Calendar.getInstance()
    private var mondayNumber = calendar.get(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_WEEK)
//    private var sundayNumber = calendar.get(Calendar.DAY_OF_MONTH) + calendar.get(Calendar.DAY_OF_WEEK) - 2

    val myLinearLayoutManager = object :
        LinearLayoutManager(inflater.context, LinearLayoutManager.HORIZONTAL, false) {

        override fun canScrollHorizontally(): Boolean {
            return false
        }
    }

    private val convertNumInDay = mapOf<Int, String>(1 to "пн", 2 to "вт", 3 to "ср", 4 to "чт", 5 to "пт", 6 to "сб", 7 to "вс")


    private val recyclerViewConstants = dateConverter()

    private fun dateConverter(): MutableList<MutableList<String>> {
        val allDate = mutableListOf<MutableList<String>>()
        val monthDay = calendar.get(Calendar.DAY_OF_MONTH)
        val currentDayNumber = calendar.get(Calendar.DAY_OF_WEEK)-1

        val monday = monthDay - currentDayNumber + 1

        for(day in 1..7){
            val dayNumber = monday + day - 1
            var currentDay = false
            if (dayNumber == monthDay)
                currentDay = true

            allDate.add(mutableListOf("${convertNumInDay[day]}", "$dayNumber", "$currentDay") )
        }
        return allDate
    }

    fun onStart(){
        dateRecyclerView.layoutManager = myLinearLayoutManager
        dateRecyclerView.adapter = RecyclerViewAdapter(recyclerViewConstants)


        //Нужно получать количество дней в текущем месяце
        //Нужно получать текущий день
        //Нужно получать название дней в конкретных числах

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