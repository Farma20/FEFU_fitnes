package com.example.fefu_fitnes.UI.Views

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fefu_fitnes.R
import com.example.fefu_fitnes.databinding.FragmentTimetableBinding
import java.util.Collections.shuffle


class TimetableFragment: Fragment() {
    private var _binding: FragmentTimetableBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView:RecyclerView
    private var adapter: RecyclerViewAdapter? = null
    private var allRecyclerItems = mutableListOf<View>()

    private lateinit var workoutRecyclerView:RecyclerView
    private var workoutAdapter: WorkoutRecyclerViewAdapter? = null

    private val recyclerViewConstants = listOf(
        listOf("пн", "1"),
        listOf("вт", "2"),
        listOf("ср", "3"),
        listOf("чт", "4"),
        listOf("пт", "5"),
        listOf("сб", "6"),
        listOf("вс", "7"),
        listOf("пн", "8"),
        listOf("вт", "9"),
        listOf("ср", "10"),
        listOf("чт", "11"),
        listOf("пт", "12"),
        listOf("сб", "13"),
        listOf("вс", "14"),
        listOf("пн", "15"),
        listOf("вт", "16"),
        listOf("ср", "17"),
        listOf("чт", "18"),
        listOf("пт", "19"),
        listOf("сб", "20"),
        listOf("вс", "21"),
        listOf("пн", "22"),
        listOf("вт", "23"),
        listOf("ср", "24"),
        listOf("чт", "25"),
        listOf("пт", "26"),
        listOf("сб", "27"),
        listOf("вс", "28"),
    )

    private val workoutRecyclerViewConstant = listOf(
        listOf("Групповое занятие по аэробике", "Кердун Юлия Олеговна", "Корпус S, зал аэробики", "14:00 - 16:00", "3/25"),
        listOf("Настольный теннис", "Арефин Олег Федорович", "Корпус S, зал тенниса", "14:30 - 16:00", "3/10"),
        listOf("Йога", "Шукурова Карина Андреевна", "Корпус S, зал аэробики", "16:00 - 17:00", "13/25"),
        listOf("Беговой клуб", "Заиченко Мария Сергеевна", "Корпус S, беговая дорожка", "17:00 - 18:00", "9/16"),
        listOf("Кроссфит", "Кустов Юрий Сергеевич", "Корпус S, зал кроссфита", "18:00 - 19:30", "13/25")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTimetableBinding.inflate(inflater, container, false)

        //отключение прокрутки recylerView
        val myLinearLayoutManager = object :
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false) {

            override fun canScrollHorizontally(): Boolean {
                return false
            }
        }

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = myLinearLayoutManager

        workoutRecyclerView = binding.workoutRecyclerView

        updateUI()
        updateWorkoutUI()

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        var recyclerItemCurrentPosition = 6

        binding.dateButtonForward.setOnClickListener {
            if (recyclerItemCurrentPosition != recyclerViewConstants.lastIndex)
                recyclerItemCurrentPosition +=7

            recyclerView.scrollToPosition(recyclerItemCurrentPosition)
        }

        binding.dateButtonBack.setOnClickListener {
            if (recyclerItemCurrentPosition - 7 > 0)
                recyclerItemCurrentPosition -=7
            else
                recyclerItemCurrentPosition = 6

            recyclerView.scrollToPosition(recyclerItemCurrentPosition-6)
        }
    }

    //date recyclerView
    private inner class RecyclerViewAdapter(val data:List<List<String>>):
        RecyclerView.Adapter<RecyclerViewHolder>(){
        @SuppressLint("SetTextI18n")
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
            val view = layoutInflater.inflate(R.layout.item_day_timetable_unchecked, parent, false)
            allRecyclerItems.add(view)
            view.setOnClickListener{
                for (item in allRecyclerItems){
                    if (item != it)
                        item.setBackgroundResource(R.drawable.timetable_item_day_unchecked)
                }
                it.setBackgroundResource(R.drawable.timetable_item_day_checked)

                updateWorkoutUI()

            }
            return RecyclerViewHolder(view)
        }


        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            val item = data[position]

            if (position == 0){
                holder.parent.setBackgroundResource(R.drawable.timetable_item_day_checked)
            }

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
        var parent:View = itemView.findViewById(R.id.parent)
        val textDay:TextView = itemView.findViewById(R.id.day_name)
        val textNumber:TextView = itemView.findViewById(R.id.day_number)
    }
    //date recyclerView

    //workout recyclerView
    private inner class WorkoutRecyclerViewAdapter(val data: List<List<String>>):
        RecyclerView.Adapter<WorkoutRecyclerViewHolder>(){
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): WorkoutRecyclerViewHolder {
            val view = layoutInflater.inflate(R.layout.item_workout_timtable, parent, false)

            return WorkoutRecyclerViewHolder(view)
        }

        override fun onBindViewHolder(holder: WorkoutRecyclerViewHolder, position: Int) {
            val item = data[position]

            if (position == 0){
                val layoutParams: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
                layoutParams.setMargins(0, 50, 0, 0)
                holder.parent.layoutParams = layoutParams
            }


            holder.workoutName.text = item[0]
            holder.trainerName.text = item[1]
            holder.location.text = item[2]
            holder.time.text = item[3]
            holder.spaceCount.text = item[4]
        }

        override fun getItemCount(): Int {
            return data.count()
        }

    }

    private inner class WorkoutRecyclerViewHolder(view: View):RecyclerView.ViewHolder(view){
        var parent: View = itemView.findViewById(R.id.parent)
        val workoutName:TextView = itemView.findViewById(R.id.near_workout_name)
        val trainerName:TextView = itemView.findViewById(R.id.near_workout_couch_name)
        val location:TextView = itemView.findViewById(R.id.near_workout_location)
        val time:TextView = itemView.findViewById(R.id.near_workout_time)
        val spaceCount:TextView = itemView.findViewById(R.id.near_workout_space_count)
    }
    //workout recyclerView

    private fun updateUI(){
        adapter = RecyclerViewAdapter(recyclerViewConstants)
        recyclerView.adapter = adapter
    }

    private fun updateWorkoutUI(){
        workoutAdapter = WorkoutRecyclerViewAdapter(workoutRecyclerViewConstant.shuffled())
        workoutRecyclerView.adapter = workoutAdapter
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object{
        fun newInstance():TimetableFragment{
            return TimetableFragment()
        }
    }
}