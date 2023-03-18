package com.example.fefu_fitnes.UI.Views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fefu_fitnes.R
import com.example.fefu_fitnes.UI.Controllers.RecyclerViews.TimetableDateRecyclerView
import com.example.fefu_fitnes.UI.Controllers.RecyclerViews.TimetableListRecyclerView
import com.example.fefu_fitnes.UI.Models.BookingDataModel
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
    private var userEventsList = listOf<BookingDataModel>()
    private var dayEventList = mutableListOf<UpdateEventDataModel>()

    private var firstButtonsCheck = true

    private val timetableViewModel: TimetableViewModel by lazy {
        ViewModelProvider(this)[TimetableViewModel::class.java]
    }

    override fun onWorkoutSelected(i: Int ) {
        for(event in allEventsList){
            if (event.eventId == i){
                timetableViewModel.pushNewBooking(event.eventId)
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

        recyclerViewClass = TimetableDateRecyclerView(inflater, binding.recyclerView)

        recyclerViewListClass = TimetableListRecyclerView(
            inflater,
            this,
            binding.workoutRecyclerView
        )

        timetableViewModel.getUserEvents().observe(viewLifecycleOwner){list->
            userEventsList = list
        }

        timetableViewModel.getEvents().observe(viewLifecycleOwner){list ->
            allEventsList = list

            recyclerViewClass.getCurrentData().observe(viewLifecycleOwner){ day ->
                dayEventList = mutableListOf<UpdateEventDataModel>()
                for(item in allEventsList){
                    if (item.date == day)
                        dayEventList.add(item)
                }
                if(firstButtonsCheck)
                    updateAllEventsList()
                else
                    updateUserEventsList()
            }
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()


        binding.dateButtonForward.setOnClickListener {
            recyclerViewClass.nextWeak()
            recyclerViewClass.onStart()
        }

        binding.dateButtonBack.setOnClickListener {
            recyclerViewClass.prevWeak()
            recyclerViewClass.onStart()
        }

        //наблюдатель за изменением текущего месяца
        recyclerViewClass.getMonth().observe(viewLifecycleOwner){
            binding.currentMonth.text = it.toString()
        }

        recyclerViewClass.onStart()

        updateAllEventsList()

        binding.allEventsButton.setOnClickListener{
            it.setBackgroundResource(R.drawable.login_fragment_enter_button)
            binding.userEventsButton.setBackgroundResource(R.drawable.login_fragment_enter_button_blue)
            setActiveButton(true)

            updateAllEventsList()
        }

        binding.userEventsButton.setOnClickListener{

            timetableViewModel.getUserEvents().observe(viewLifecycleOwner){list->
                userEventsList = list
            }
            it.setBackgroundResource(R.drawable.login_fragment_enter_button)
            binding.allEventsButton.setBackgroundResource(R.drawable.login_fragment_enter_button_blue)
            setActiveButton(false)
            updateUserEventsList()
        }
    }

    private fun updateUserEventsList() {
        val indexes = mutableListOf<Int>()
        for(item in userEventsList)
            indexes.add(item.eventId)

        val events = mutableListOf<UpdateEventDataModel>()
        for(item in dayEventList){
            if (item.eventId in indexes)
                events.add(item)
        }
        recyclerViewListClass.onStart(events)
    }

    private fun setActiveButton(state:Boolean){
        firstButtonsCheck = state
    }

    private fun updateAllEventsList() {
        recyclerViewListClass.onStart(dayEventList)
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