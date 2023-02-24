package com.example.fefu_fitnes.UI.Views

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.opengl.Visibility
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
import com.example.fefu_fitnes.databinding.DialogFragmentWorkoutAllInfoBinding
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException


class WorkoutInfoAllDialogFragment(val dialogWorkoutData:WorkoutDataModel):DialogFragment() {

    private lateinit var hostActivity: MainActivity

    lateinit var recyclerView: RecyclerView

    private var _binding: DialogFragmentWorkoutAllInfoBinding? = null
    private val binding get() = _binding!!

    interface Callback{
        fun onWorkoutSelected(i:Int)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogFragmentWorkoutAllInfoBinding.inflate(inflater, container, false)

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

    override fun onStart() {
        super.onStart()
        binding.nearWorkoutName.text = dialogWorkoutData.workoutName
        binding.nearWorkoutSpaceCount.text = dialogWorkoutData.freeSpaces
        binding.nearWorkoutTime.text = dialogWorkoutData.workoutTime
        binding.nearWorkoutCouchName.text = dialogWorkoutData.couchName
        binding.couchNumber.text = dialogWorkoutData.couchPhone
        binding.couchMail.text = dialogWorkoutData.couchEmail
        binding.nearWorkoutLocation.text = dialogWorkoutData.workoutLocation
        binding.workoutDescription.text = dialogWorkoutData.workoutDescription

        binding.nearWorkoutPaymentStatusPaid3.setOnClickListener{
//            hostActivity.mainViewModel.sendNewBooking(1,1)

            binding.nearWorkoutPaymentStatusPaid3.visibility = View.GONE
            binding.nearWorkoutPaymentStatusPaid.visibility = View.VISIBLE

            targetFragment?.let{fragment ->
                (fragment as Callback).onWorkoutSelected(dialogWorkoutData.workoutId)
            }

            Thread {
                hostActivity.mainViewModel.postMessage(dialogWorkoutData.workoutId)
            }.start()
        }
    }

    override fun getTheme() = R.style.MyDialog

    override fun onAttach(context: Context) {
        super.onAttach(context)
        hostActivity = context as MainActivity
    }

    override fun onResume() {
        super.onResume()
        

        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
    }


    companion object{
        fun newInstance(workoutData: WorkoutDataModel):WorkoutInfoAllDialogFragment{

            return WorkoutInfoAllDialogFragment(workoutData)
        }
    }
    
}