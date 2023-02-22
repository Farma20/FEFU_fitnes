package com.example.fefu_fitnes.UI.Views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.fefu_fitnes.UI.Controllers.RecyclerViews.PaymentsRecyclerView
import com.example.fefu_fitnes.databinding.FragmentPaimentsBinding

class PaymentsFragment: Fragment() {

    private lateinit var recyclerView: PaymentsRecyclerView

    private var _binding: FragmentPaimentsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaimentsBinding.inflate(inflater, container, false)

        recyclerView = PaymentsRecyclerView(inflater, binding.recyclerView)
        recyclerView.onStart()

        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object{
        fun newInstance():PaymentsFragment{
            return PaymentsFragment()
        }
    }

}