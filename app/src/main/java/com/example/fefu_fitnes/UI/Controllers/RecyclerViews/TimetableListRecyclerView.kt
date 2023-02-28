package com.example.fefu_fitnes.UI.Controllers.RecyclerViews

import android.text.format.Time
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.fefu_fitnes.R
import com.example.fefu_fitnes.UI.Models.WorkoutDataModel
import com.example.fefu_fitnes.UI.Views.TimetableFragment
import com.example.fefu_fitnes.UI.Views.WorkoutInfoAllDialogFragment

class TimetableListRecyclerView(
    val inflater: LayoutInflater,
    val fragment: TimetableFragment,
    val recyclerView: RecyclerView) {

    private var allWorkoutItems = mutableListOf<View>()

    fun onStart(workoutData:List<WorkoutDataModel>){
        recyclerView.adapter = WorkoutRecyclerViewAdapter(workoutData)
    }


    private inner class WorkoutRecyclerViewAdapter(val data: List<WorkoutDataModel>):
        RecyclerView.Adapter<WorkoutRecyclerViewHolder>(){
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): WorkoutRecyclerViewHolder {
            val view = inflater.inflate(R.layout.item_workout_timtable, parent, false)
            allWorkoutItems.add(view)
            view.setOnClickListener {
                val dialogFragment =  WorkoutInfoAllDialogFragment.newInstance(data[viewType])
                dialogFragment.setTargetFragment(fragment, 1)
                dialogFragment.show(fragment.requireFragmentManager(), "const")
            }

            return WorkoutRecyclerViewHolder(view)
        }

        override fun getItemViewType(position: Int): Int {
            return position
        }

        override fun onBindViewHolder(holder: WorkoutRecyclerViewHolder, position: Int) {
            val item = data[position]

            if (position == 0){
                val layoutParams: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
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
        var  workoutName: TextView = itemView.findViewById(R.id.near_workout_name)
        var  trainerName: TextView = itemView.findViewById(R.id.near_workout_couch_name)
        var  location: TextView = itemView.findViewById(R.id.near_workout_location)
        var  time: TextView = itemView.findViewById(R.id.near_workout_time)
        var  spaceCount: TextView = itemView.findViewById(R.id.near_workout_space_count)
    }
}