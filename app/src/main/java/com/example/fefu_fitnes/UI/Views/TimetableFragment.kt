package com.example.fefu_fitnes.UI.Views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fefu_fitnes.UI.Controllers.RecyclerViews.TimetableDateRecyclerView
import com.example.fefu_fitnes.UI.Controllers.RecyclerViews.TimetableListRecyclerView
import com.example.fefu_fitnes.UI.Models.UpdateEventDataModel
import com.example.fefu_fitnes.UI.ViewModels.TimetableViewModel
import com.example.fefu_fitnes.databinding.FragmentTimetableBinding



class TimetableFragment: Fragment(), WorkoutInfoAllDialogFragment.Callback {

    private var _binding: FragmentTimetableBinding? = null
    private val binding get() = _binding!!
    private lateinit var hostActivity: AppActivity
    private lateinit var recyclerViewClass: TimetableDateRecyclerView
    private lateinit var recyclerViewListClass: TimetableListRecyclerView
    private var allEventsList = listOf<UpdateEventDataModel>()

    private val timetableViewModel: TimetableViewModel by lazy {
        ViewModelProvider(this)[TimetableViewModel::class.java]
    }

    override fun onWorkoutSelected( i: Int ) {
            for(event in allEventsList){
            if (event.eventId == i){
                timetableViewModel.setMainEvent(event)
                break
            }
        }
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

        recyclerViewClass = TimetableDateRecyclerView(inflater, binding.recyclerView)

        recyclerViewListClass = TimetableListRecyclerView(
            inflater,
            this,
            binding.workoutRecyclerView
        )

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        var recyclerItemCurrentPosition = 6

        binding.dateButtonForward.setOnClickListener {
            if (recyclerItemCurrentPosition != 27)
                recyclerItemCurrentPosition +=7

            binding.recyclerView.scrollToPosition(recyclerItemCurrentPosition)
        }

        binding.dateButtonBack.setOnClickListener {
            if (recyclerItemCurrentPosition - 7 > 0)
                recyclerItemCurrentPosition -=7
            else
                recyclerItemCurrentPosition = 6

            binding.recyclerView.scrollToPosition(recyclerItemCurrentPosition-6)
        }

        recyclerViewClass.onStart()

        recyclerViewClass.getCurrentData().observe(this){ day ->
            timetableViewModel.getEvents().observe(this){list ->
                allEventsList = list
                val dayEventList = mutableListOf<UpdateEventDataModel>()
                for(item in allEventsList){
                    if (item.date.toInt() == day) {
                        dayEventList.add(item)
                    }
                }
                recyclerViewListClass.onStart(dayEventList)
            }
        }


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        hostActivity = context as AppActivity
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