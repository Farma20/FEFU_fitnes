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

private val convertNumInDay = mapOf<Int, String>(1 to "пн", 2 to "вт", 3 to "ср", 4 to "чт", 5 to "пт", 6 to "сб", 7 to "вс")

private val convertNumInMonth = mapOf<Int, String>(
    0 to "Январь",
    1 to "Фервраль",
    2 to "Март",
    3 to "Апрель",
    4 to "Май",
    5 to "Июнь",
    6 to "Июль",
    7 to "Август",
    8 to "Сентябрь",
    9 to "Октябрь",
    10 to "Ноябрь",
    11 to "Декабрь",
)

private val getMonthInDayCount = mapOf<Int, Int>(
    0 to 31,
    1 to 28,
    2 to 31,
    3 to 30,
    4 to 31,
    5 to 30,
    6 to 31,
    7 to 31,
    8 to 30,
    9 to 31,
    10 to 30,
    11 to 31,
)


class TimetableDateRecyclerView(
    val inflater: LayoutInflater,
    private val dateRecyclerView: RecyclerView){

    private var changeMonth = 0
    private var changeDay = 0

    private var currentData = MutableLiveData<Int>().apply {
        value = 3
    }
    private var allRecyclerItems = mutableListOf<View>()

    private val calendar:Calendar = Calendar.getInstance()

    private var monthNumber = calendar.get(Calendar.MONTH)

    //перменная, хранящая название текущего месяца
    private val monthName = MutableLiveData<String>().apply {
        value = convertNumInMonth[monthNumber]
    }

    //Переменная, храняща число текущего понедельника
    private var mondayNumber = calendar.get(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_WEEK) + 2

    private val myLinearLayoutManager = object :
        LinearLayoutManager(inflater.context, LinearLayoutManager.HORIZONTAL, false) {

        override fun canScrollHorizontally(): Boolean {
            return false
        }
    }


    private var recyclerViewConstants = dateConverter()

    private fun dateConverter(): MutableList<MutableList<String>> {
        val allDate = mutableListOf<MutableList<String>>()
        val monthDay = getMonthInDayCount[monthNumber]
        for(day in 1..7){
            var dayNumber = mondayNumber + day - 1

            if(dayNumber > monthDay!!){
                dayNumber -= monthDay
                changeMonth = 1
                changeDay++
                println("_________________________${changeMonth}_____________________________________")
            }

            if(dayNumber < 1){
                dayNumber += getMonthInDayCount[monthNumber-1]!!
                changeMonth = -1
                changeDay++
                println("_________________________${changeMonth}_____________________________________")
            }

            var currentDay = false
            if (dayNumber == calendar.get(Calendar.DAY_OF_MONTH))
                currentDay = true

            allDate.add(mutableListOf("${convertNumInDay[day]}", "$dayNumber", "$currentDay"))
        }

        if (changeDay > 3)
            if(changeMonth == -1)
                monthName.value = convertNumInMonth[monthNumber-1]
            else
                monthName.value = convertNumInMonth[monthNumber+1]
            changeDay = 0

        return allDate
    }

    fun nextWeak(){
        if(changeMonth == 1){
            mondayNumber -= getMonthInDayCount[monthNumber]!!
            monthNumber += 1
            monthName.value = convertNumInMonth[monthNumber]
            changeMonth = 0
        }
        mondayNumber += 7
        recyclerViewConstants = dateConverter()
    }

    fun prevWeak(){
        if(changeMonth == -1){
            monthNumber -= 1
            mondayNumber += getMonthInDayCount[monthNumber]!!
            monthName.value = convertNumInMonth[monthNumber]
            changeMonth = 0
        }
        mondayNumber -= 7
        recyclerViewConstants = dateConverter()
    }

    fun getMonth(): LiveData<String> {
        return monthName
    }

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