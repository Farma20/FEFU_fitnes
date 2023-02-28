package com.example.fefu_fitnes.UI.Views

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.fefu_fitnes.UI.Controllers.RecyclerViews.MainMenuRecyclerView
import com.example.fefu_fitnes.UI.ViewModels.MainMenuViewModel
import com.example.fefu_fitnes.databinding.FragmentMainMenuBinding

const val WORKOUT_INFO = "workout_info"


class MainMenuFragment: Fragment() {

    private lateinit var hostActivity: MainActivity

    private var _binding: FragmentMainMenuBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerClass: MainMenuRecyclerView

    val mainMenuViewModel: MainMenuViewModel by lazy {
        ViewModelProvider(this)[MainMenuViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

//        val result = hostActivity.mainViewModel.getUserData()
//        result.observe(this.viewLifecycleOwner) { res ->
//
//            hostActivity.mainViewModel.user = UserDataModel(
//                firstName = res.firstName,
//                secondName = res.secondName,
//                cardNumber = res.cardNumber,
//                workoutCount = res.workoutCount
//            )
//            println(res)
//            updateUI()
//
//        }

        _binding = FragmentMainMenuBinding.inflate(inflater, container, false)

        recyclerView = binding.mainRecyclerView

        recyclerClass = MainMenuRecyclerView(inflater, recyclerView)
        recyclerClass.onStart(mainMenuViewModel.currentEvents)

        updateUI()

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()

        if(mainMenuViewModel.currentWorkout.workoutName != ""){

            binding.nearWorkoutHolder.setOnClickListener{
                WorkoutInfoDialogFragment.newInstance(mainMenuViewModel.currentWorkout).show(
                    this@MainMenuFragment.requireFragmentManager(), WORKOUT_INFO
                )
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        hostActivity = context as MainActivity
    }

    @SuppressLint("SetTextI18n")
    private fun updateUI(){
        recyclerClass.onStart(mainMenuViewModel.currentEvents)

        binding.nameText.text = "${mainMenuViewModel.currentUser.firstName}, ваша карта"
        binding.cardNumber.text = mainMenuViewModel.currentUser.cardNumber
        binding.workoutCount.text = mainMenuViewModel.currentUser.workoutCount

        if(mainMenuViewModel.currentWorkout.workoutName != ""){

            binding.nearWorkoutHolderHide.visibility = View.GONE
            binding.nearWorkoutTextHide.visibility = View.GONE
            binding.nearWorkoutSpaceCountTitle.visibility = View.VISIBLE

            binding.nearWorkoutName.text = mainMenuViewModel.currentWorkout.workoutName
            binding.nearWorkoutTime.text = mainMenuViewModel.currentWorkout.workoutTime
            binding.nearWorkoutLocation.text = mainMenuViewModel.currentWorkout.workoutLocation
            binding.nearWorkoutCouchName.text = mainMenuViewModel.currentWorkout.couchName
            binding.nearWorkoutSpaceCount.text = mainMenuViewModel.currentWorkout.freeSpaces
            binding.nearWorkoutPaymentStatusPaid.text = mainMenuViewModel.currentWorkout.paymentStatus
        }
    }



    companion object {
        fun newInstance(): MainMenuFragment {
            return MainMenuFragment()
        }
    }
}