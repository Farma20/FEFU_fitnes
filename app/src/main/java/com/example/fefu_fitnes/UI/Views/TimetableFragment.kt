package com.example.fefu_fitnes.UI.Views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.fefu_fitnes.UI.Controllers.RecyclerViews.TimetableDateRecyclerView
import com.example.fefu_fitnes.UI.Controllers.RecyclerViews.TimetableListRecyclerView
import com.example.fefu_fitnes.UI.Models.WorkoutDataModel
import com.example.fefu_fitnes.UI.ViewModels.TimetableViewModel
import com.example.fefu_fitnes.databinding.FragmentTimetableBinding


class TimetableFragment: Fragment(), WorkoutInfoAllDialogFragment.Callback {
    private var _binding: FragmentTimetableBinding? = null
    private val binding get() = _binding!!

    lateinit var hostActivity: MainActivity

    private lateinit var recyclerView:RecyclerView

    private lateinit var recyclerViewClass: TimetableDateRecyclerView
    private lateinit var recyclerViewListClass: TimetableListRecyclerView

    private lateinit var workoutRecyclerViewConstant: List<WorkoutDataModel>

    private val timetableViewModel: TimetableViewModel by lazy {
        ViewModelProvider(this)[TimetableViewModel::class.java]
    }

    override fun onWorkoutSelected( i: Int ) {
        hostActivity.mainViewModel.allWorkout[i].paymentStatus = "Вы записаны"
        hostActivity.mainViewModel.nearWorkout = hostActivity.mainViewModel.allWorkout[i-1]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTimetableBinding.inflate(inflater, container, false)

//        val result = hostActivity.mainViewModel.getWorkout()
//        result.observe(this.viewLifecycleOwner) { res ->
//
//            hostActivity.mainViewModel.allWorkout = res.asList()
//            updateUI()
//            updateWorkoutUI()
//        }


        recyclerView = binding.recyclerView


        recyclerViewClass = TimetableDateRecyclerView(inflater, recyclerView)
        recyclerViewClass.onStart()

        recyclerViewListClass = TimetableListRecyclerView(
            inflater,
            this@TimetableFragment,
            binding.workoutRecyclerView)
        recyclerViewListClass.onStart(timetableViewModel.allWorkout)

        updateUI()

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        var recyclerItemCurrentPosition = 6

        binding.dateButtonForward.setOnClickListener {
            if (recyclerItemCurrentPosition != 27)
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




    private fun updateUI(){
//        workoutRecyclerViewConstant = hostActivity.mainViewModel.allWorkout
//        adapter = RecyclerViewAdapter(recyclerViewConstants)
//        recyclerView.adapter = adapter
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