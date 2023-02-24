package com.example.fefu_fitnes.UI.Views

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
import com.example.fefu_fitnes.UI.Models.WorkoutDataModel
import com.example.fefu_fitnes.databinding.DialogFragmentWorkoutInfoBinding
import com.example.fefu_fitnes.databinding.FragmentMainMenuBinding

class WorkoutInfoDialogFragment(val dialogWorkoutData:WorkoutDataModel):DialogFragment() {

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
        WorkoutInfoDialogRecyclerView(inflater, recyclerView).onStart(dialogWorkoutData.workoutPhotos)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.nearWorkoutName.text = dialogWorkoutData.workoutName
        binding.nearWorkoutSpaceCount.text = dialogWorkoutData.freeSpaces
        binding.nearWorkoutTime.text = dialogWorkoutData.workoutTime
        binding.nearWorkoutPaymentStatusPaid.text = dialogWorkoutData.paymentStatus
        binding.nearWorkoutCouchName.text = dialogWorkoutData.couchName
        binding.couchNumber.text = dialogWorkoutData.couchPhone
        binding.couchMail.text = dialogWorkoutData.couchEmail
        binding.nearWorkoutLocation.text = dialogWorkoutData.workoutLocation
        binding.workoutDescription.text = dialogWorkoutData.workoutDescription
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
        fun newInstance(workoutData: WorkoutDataModel):WorkoutInfoDialogFragment{

            return WorkoutInfoDialogFragment(workoutData)
        }
    }
    
}