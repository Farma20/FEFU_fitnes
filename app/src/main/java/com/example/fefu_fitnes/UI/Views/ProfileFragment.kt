package com.example.fefu_fitnes.UI.Views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fefu_fitnes.UI.Controllers.RecyclerViews.PaymentsRecyclerView
import com.example.fefu_fitnes.databinding.FragmentPaymentsBinding
import com.example.fefu_fitnes.databinding.FragmentProfileBinding

class ProfileFragment: Fragment() {

    private lateinit var recyclerView: PaymentsRecyclerView

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)


        return binding.root
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