package com.example.fefu_fitnes.UI.Controllers.RecyclerViews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.fefu_fitnes.R

class WorkoutInfoDialogRecyclerView(val inflater: LayoutInflater, private val recyclerView: RecyclerView) {


    fun onStart(photos:List<Int>) {
        recyclerView.adapter = WorkoutDialogAdapter(photos.shuffled())
    }

    private inner class WorkoutDialogAdapter(val photos: List<Int>):
        RecyclerView.Adapter<ViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = inflater.inflate(R.layout.item_workout_info_photo, parent, false)
            return WorkoutDialogHolder(view)
        }

        override fun getItemCount(): Int {
            return photos.count()
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            if(holder is WorkoutDialogHolder){
                holder.photoView.setBackgroundResource(photos[position])
            }
        }

    }

    private inner class WorkoutDialogHolder(view: View):ViewHolder(view){
        var photoView: ImageView = itemView.findViewById(R.id.photo)
    }
}