package com.example.fefu_fitnes.UI.Views

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.fefu_fitnes.R
import com.example.fefu_fitnes.UI.Controllers.RecyclerViews.WorkoutInfoDialogRecyclerView
import com.example.fefu_fitnes.UI.Models.UpdateEventDataModel
import com.example.fefu_fitnes.UI.Models.WorkoutDataModel
import com.example.fefu_fitnes.databinding.DialogFragmentWorkoutInfoBinding
import com.example.fefu_fitnes.databinding.FragmentMainMenuBinding

class WorkoutInfoDialogFragment(private val dialogEventData: UpdateEventDataModel):DialogFragment() {

    lateinit var recyclerView: RecyclerView

    private var _binding: DialogFragmentWorkoutInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogFragmentWorkoutInfoBinding.inflate(inflater, container, false)

        recyclerView = binding.recyclerViewPhotos
        WorkoutInfoDialogRecyclerView(inflater, recyclerView).onStart(
            listOf(
                R.drawable.workout_photo_1,
                R.drawable.workout_photo_2,
                R.drawable.workout_photo_3,
                R.drawable.workout_photo_4,
                R.drawable.workout_photo_5
            ))

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()
        binding.nearWorkoutName.text = dialogEventData.eventName
        binding.nearWorkoutSpaceCount.text = dialogEventData.occupiedSpaces.toString() + "/"+ dialogEventData.totalSpaces.toString()
        binding.nearWorkoutTime.text = dialogEventData.startEventTime + " - " + dialogEventData.endEventTime
        binding.nearWorkoutCouchName.text = dialogEventData.couchName
        binding.couchNumber.text = dialogEventData.couchPhone
        binding.couchMail.text = dialogEventData.couchEmail
        binding.nearWorkoutLocation.text = dialogEventData.eventLocation
        binding.workoutDescription.text = dialogEventData.eventDescription
    }

    override fun getTheme() = R.style.MyDialog

    override fun onResume() {
        super.onResume()
        

        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
    }


    companion object{
        fun newInstance(eventData: UpdateEventDataModel):WorkoutInfoDialogFragment{

            return WorkoutInfoDialogFragment(eventData)
        }
    }
    
}