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
import java.time.LocalDate
import java.util.Calendar
import kotlin.time.Duration.Companion.days

private val convertNumInDay = mapOf<Int, String>(1 to "пн", 2 to "вт", 3 to "ср", 4 to "чт", 5 to "пт", 6 to "сб", 7 to "вс")

private val convertNumInMonth = mapOf<Int, String>(
    1 to "Январь",
    2 to "Фервраль",
    3 to "Март",
    4 to "Апрель",
    5 to "Май",
    6 to "Июнь",
    7 to "Июль",
    8 to "Август",
    9 to "Сентябрь",
    10 to "Октябрь",
    11 to "Ноябрь",
    12 to "Декабрь",
)


class TimetableDateRecyclerView(
    val inflater: LayoutInflater,
    private val dateRecyclerView: RecyclerView){

    private var allRecyclerItems = mutableListOf<View>()

    private var currentDate = LocalDate.now()
    private var monday = currentDate.minusDays((currentDate.dayOfWeek.value - 1).toLong())
    private var sunday = monday.plusDays(6)
    private var currentMonth = currentDate.month.value
    private var viewMonth = currentDate.month.value
    private var daysOfAnotherMonthCount = 0

    //перменная, хранящая название текущего месяца
    private val monthName = MutableLiveData<String>().apply {
        value = convertNumInMonth[viewMonth]
    }

    private var currentData = MutableLiveData<Int>().apply {
        value = 0
    }



    private val myLinearLayoutManager = object :
        LinearLayoutManager(inflater.context, LinearLayoutManager.HORIZONTAL, false) {

        override fun canScrollHorizontally(): Boolean {
            return false
        }
    }


    private var recyclerViewConstants = dateConverter()


    private fun dateConverter(): MutableList<MutableList<String>> {
        val allDate = mutableListOf<MutableList<String>>()

        for(day in 1..7){

            var dayNumber = monday.dayOfMonth + day - 1

            if(dayNumber > monday.lengthOfMonth()){
                dayNumber -= monday.lengthOfMonth()
                daysOfAnotherMonthCount++
            }

            val selected = if(dayNumber == currentDate.dayOfMonth && currentMonth == viewMonth){
                "true"
            }else{
                "false"
            }

            allDate.add(mutableListOf("${convertNumInDay[day]}", "$dayNumber", selected))
        }


        return allDate
    }

    fun nextWeak(){
        monday = monday.plusDays(7)
        sunday = sunday.plusDays(7)
        recyclerViewConstants = dateConverter()

        viewMonth = monday.month.value
        if(daysOfAnotherMonthCount > 3){
            viewMonth += 1
        }
        monthName.value = convertNumInMonth[viewMonth]

        daysOfAnotherMonthCount = 0
    }

    fun prevWeak(){
        monday = monday.minusDays(7)
        sunday = sunday.minusDays(7)
        recyclerViewConstants = dateConverter()

        if(daysOfAnotherMonthCount < 4){
            viewMonth = monday.month.value
        }
        monthName.value = convertNumInMonth[viewMonth]

        daysOfAnotherMonthCount = 0
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