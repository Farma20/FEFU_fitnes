package com.example.fefu_fitnes.UI.Views

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
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
import com.example.fefu_fitnes.UI.Models.UserDataModel
import com.example.fefu_fitnes.UI.Models.WorkoutDataModel
import com.example.fefu_fitnes.databinding.FragmentTimetableBinding
import java.util.Collections.shuffle


class TimetableFragment: Fragment(), WorkoutInfoAllDialogFragment.Callback {
    private var _binding: FragmentTimetableBinding? = null
    private val binding get() = _binding!!

    lateinit var hostActivity: MainActivity

    private lateinit var recyclerView:RecyclerView
    private var adapter: RecyclerViewAdapter? = null
    private var allRecyclerItems = mutableListOf<View>()
    private var allWorkoutItems = mutableListOf<View>()

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

    override fun onWorkoutSelected( i: Int ) {
        hostActivity.mainViewModel.allWorkout[i-1].paymentStatus = "Вы записаны"
        hostActivity.mainViewModel.nearWorkout = hostActivity.mainViewModel.allWorkout[i-1]
        updateWorkoutUI()
    }

    private lateinit var workoutRecyclerViewConstant: List<WorkoutDataModel>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTimetableBinding.inflate(inflater, container, false)

        val result = hostActivity.mainViewModel.getWorkout()
        result.observe(this.viewLifecycleOwner) { res ->

            hostActivity.mainViewModel.allWorkout = res.asList()
            updateUI()
            updateWorkoutUI()
        }

        //отключение прокрутки recylerView
        val myLinearLayoutManager = object :
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false) {

            override fun canScrollHorizontally(): Boolean {
                return false
            }
        }

        workoutRecyclerViewConstant = hostActivity.mainViewModel.allWorkout

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
    private inner class WorkoutRecyclerViewAdapter(val data: List<WorkoutDataModel>):
        RecyclerView.Adapter<WorkoutRecyclerViewHolder>(){
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): WorkoutRecyclerViewHolder {
            val view = layoutInflater.inflate(R.layout.item_workout_timtable, parent, false)
            allWorkoutItems.add(view)
            view.setOnClickListener {
                val dialogFragment =  WorkoutInfoAllDialogFragment.newInstance(hostActivity.mainViewModel.allWorkout[viewType])
                dialogFragment.setTargetFragment(this@TimetableFragment, 1)
                dialogFragment.show(this@TimetableFragment.requireFragmentManager(), "const")
            }

            return WorkoutRecyclerViewHolder(view)
        }

        override fun getItemViewType(position: Int): Int {
            return position
        }

        override fun onBindViewHolder(holder: WorkoutRecyclerViewHolder, position: Int) {
            val item = data[position]

            if (position == 0){
                val layoutParams: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
                layoutParams.setMargins(0, 50, 0, 0)
                holder.parent.layoutParams = layoutParams
            }


            holder.workoutName.text = item.workoutName
            holder.trainerName.text = item.couchName
            holder.location.text = item.workoutLocation
            holder.time.text = item.workoutTime
            holder.spaceCount.text = item.freeSpaces

            if(item.paymentStatus == "Вы записаны"){
                holder.status.visibility = View.GONE
                holder.statusOk.visibility = View.VISIBLE
            }
        }

        override fun getItemCount(): Int {
            return data.count()
        }

    }

    private inner class WorkoutRecyclerViewHolder(view: View):RecyclerView.ViewHolder(view){
        var parent: View = itemView.findViewById(R.id.parent)
        var status: TextView = itemView.findViewById(R.id.near_workout_payment_status_paid)
        var statusOk: TextView = itemView.findViewById(R.id.near_workout_payment_status_paid_ok)
        var  workoutName:TextView = itemView.findViewById(R.id.near_workout_name)
        var  trainerName:TextView = itemView.findViewById(R.id.near_workout_couch_name)
        var  location:TextView = itemView.findViewById(R.id.near_workout_location)
        var  time:TextView = itemView.findViewById(R.id.near_workout_time)
        var  spaceCount:TextView = itemView.findViewById(R.id.near_workout_space_count)
    }
    //workout recyclerView

    private fun updateUI(){
        workoutRecyclerViewConstant = hostActivity.mainViewModel.allWorkout
        adapter = RecyclerViewAdapter(recyclerViewConstants)
        recyclerView.adapter = adapter
    }

    private fun updateWorkoutUI(){
        workoutAdapter = WorkoutRecyclerViewAdapter(workoutRecyclerViewConstant)
        workoutRecyclerView.adapter = workoutAdapter
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        hostActivity = context as MainActivity
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