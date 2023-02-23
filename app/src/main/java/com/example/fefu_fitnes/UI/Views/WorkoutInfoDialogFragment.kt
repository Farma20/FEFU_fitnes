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

class WorkoutInfoDialogFragment:DialogFragment() {

    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_fragment_workout_info, container, false)

        recyclerView = view.findViewById(R.id.recycler_view_photos)
        WorkoutInfoDialogRecyclerView(inflater, recyclerView).onStart()

        return view
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
        fun newInstance():WorkoutInfoDialogFragment{
            return WorkoutInfoDialogFragment()
        }
    }
    
}