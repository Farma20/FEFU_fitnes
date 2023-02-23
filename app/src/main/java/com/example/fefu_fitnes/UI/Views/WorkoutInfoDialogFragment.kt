package com.example.fefu_fitnes.UI.Views

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.fefu_fitnes.R

class WorkoutInfoDialogFragment:DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_fragment_workout_info, container, false)

        return view
    }


    override fun onResume() {
        super.onResume()

//        dialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
    }


    companion object{
        fun newInstance():WorkoutInfoDialogFragment{
            return WorkoutInfoDialogFragment()
        }
    }
    
}