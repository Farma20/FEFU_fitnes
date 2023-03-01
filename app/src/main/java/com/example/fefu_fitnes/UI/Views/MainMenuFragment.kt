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

    private val mainMenuViewModel: MainMenuViewModel by lazy {
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

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()

        mainMenuViewModel.getUser().observe(this){
            binding.nameText.text = "${it.firstName}, ваша карта"
            binding.cardNumber.text = it.cardNumber
            binding.workoutCount.text = it.workoutCount
        }

        mainMenuViewModel.getEvents().observe(this){
            recyclerClass.onStart(it)
        }

        mainMenuViewModel.getWorkout().observe(this){
            val data = it
            if(data.workoutName != ""){

                binding.apply {
                    nearWorkoutHolder.setOnClickListener{
                        WorkoutInfoDialogFragment.newInstance(data).show(
                            this@MainMenuFragment.requireFragmentManager(), WORKOUT_INFO
                        )
                    }
                    nearWorkoutHolderHide.visibility = View.GONE
                    nearWorkoutTextHide.visibility = View.GONE
                    nearWorkoutSpaceCountTitle.visibility = View.VISIBLE

                    nearWorkoutName.text = data.workoutName
                    nearWorkoutTime.text = data.workoutTime
                    nearWorkoutLocation.text = data.workoutLocation
                    nearWorkoutCouchName.text = data.couchName
                    nearWorkoutSpaceCount.text = data.freeSpaces
                    nearWorkoutPaymentStatusPaid.text = data.paymentStatus
                }
            }
        }


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        hostActivity = context as MainActivity
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


    companion object {
        fun newInstance(): MainMenuFragment {
            return MainMenuFragment()
        }
    }
}