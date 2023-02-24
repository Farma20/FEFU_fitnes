package com.example.fefu_fitnes.UI.Views

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.fefu_fitnes.R
import com.example.fefu_fitnes.UI.Models.EventsDataModel
import com.example.fefu_fitnes.UI.Models.UserDataModel
import com.example.fefu_fitnes.databinding.FragmentMainMenuBinding

const val WORKOUT_INFO = "workout_info"


class MainMenuFragment: Fragment() {

    private lateinit var hostActivity: MainActivity

    private var _binding: FragmentMainMenuBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private  var adapter: RecyclerAdapter? = null




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val result = hostActivity.mainViewModel.getUserData()
        result.observe(this.viewLifecycleOwner) { res ->

            hostActivity.mainViewModel.user = UserDataModel(
                firstName = res.firstName,
                secondName = res.secondName,
                cardNumber = res.cardNumber,
                workoutCount = res.workoutCount
            )
            println(res)
            updateUI()

        }

        _binding = FragmentMainMenuBinding.inflate(inflater, container, false)

        recyclerView = binding.mainRecyclerView

        updateUI()

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()

        binding.nearWorkoutHolder.setOnClickListener{
            WorkoutInfoDialogFragment.newInstance(hostActivity.mainViewModel.nearWorkout).show(
                this@MainMenuFragment.requireFragmentManager(), WORKOUT_INFO
            )
        }

        binding.nameText.text = "${hostActivity.mainViewModel.user.firstName}, ваша карта"
        binding.cardNumber.text = hostActivity.mainViewModel.user.cardNumber
        binding.workoutCount.text = hostActivity.mainViewModel.user.cardNumber

        binding.nearWorkoutName.text = hostActivity.mainViewModel.nearWorkout.workoutName
        binding.nearWorkoutTime.text = hostActivity.mainViewModel.nearWorkout.workoutTime
        binding.nearWorkoutLocation.text =hostActivity.mainViewModel.nearWorkout.workoutLocation
        binding.nearWorkoutCouchName.text = hostActivity.mainViewModel.nearWorkout.couchName
        binding.nearWorkoutSpaceCount.text = hostActivity.mainViewModel.nearWorkout.freeSpaces
        binding.nearWorkoutPaymentStatusPaid.text = hostActivity.mainViewModel.nearWorkout.freeSpaces

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        hostActivity = context as MainActivity
    }

    private fun updateUI(){
        if(hostActivity.mainViewModel.nearWorkout.workoutName != ""){
            adapter = RecyclerAdapter(hostActivity.mainViewModel.events)
            recyclerView.adapter = adapter

            binding.nearWorkoutHolderHide.visibility = View.GONE
            binding.nearWorkoutTextHide.visibility = View.GONE
            binding.nearWorkoutSpaceCountTitle.visibility = View.VISIBLE

            binding.nameText.text = "${hostActivity.mainViewModel.user.firstName}, ваша карта"
            binding.cardNumber.text = hostActivity.mainViewModel.user.cardNumber
            binding.workoutCount.text = hostActivity.mainViewModel.user.cardNumber

            binding.nearWorkoutName.text = hostActivity.mainViewModel.nearWorkout.workoutName
            binding.nearWorkoutTime.text = hostActivity.mainViewModel.nearWorkout.workoutTime
            binding.nearWorkoutLocation.text =hostActivity.mainViewModel.nearWorkout.workoutLocation
            binding.nearWorkoutCouchName.text = hostActivity.mainViewModel.nearWorkout.couchName
            binding.nearWorkoutSpaceCount.text = hostActivity.mainViewModel.nearWorkout.freeSpaces
            binding.nearWorkoutPaymentStatusPaid.text = hostActivity.mainViewModel.nearWorkout.paymentStatus
        }
    }

    private inner class RecyclerAdapter(var events:List<EventsDataModel>):
        RecyclerView.Adapter<RecyclerViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
            val view = layoutInflater.inflate(R.layout.item_recycler_view, parent, false)
            return RecyclerViewHolder(view)
        }

        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            val event = events[position]
            holder.apply {
                text1.text = event.textEvent
            }
        }

        override fun getItemCount(): Int = events.count()

    }

    private inner class RecyclerViewHolder(view: View): RecyclerView.ViewHolder(view){

        val text1: TextView = itemView.findViewById(R.id.text1)

    }



    companion object {
        fun newInstance(): MainMenuFragment {
            return MainMenuFragment()
        }
    }
}