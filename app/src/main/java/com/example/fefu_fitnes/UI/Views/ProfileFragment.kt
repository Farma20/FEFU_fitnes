package com.example.fefu_fitnes.UI.Views

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fefu_fitnes.UI.ViewModels.ProfileViewModel
import com.example.fefu_fitnes.databinding.FragmentProfileBinding

class ProfileFragment: Fragment() {

    private lateinit var hostActivity: AppActivity

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val profileViewModel: ProfileViewModel by lazy{
        ViewModelProvider(this)[ProfileViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()

        profileViewModel.getUser().observe(this) {
            binding.name.text = "${it.firstName} ${it.secondName}"
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
        fun newInstance():ProfileFragment{
            return ProfileFragment()
        }
    }

}