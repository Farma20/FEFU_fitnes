package com.example.fefu_fitnes.UI.Views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fefu_fitnes.UI.Controllers.RecyclerViews.PaymentsRecyclerView
import com.example.fefu_fitnes.UI.ViewModels.ServicesViewModel
import com.example.fefu_fitnes.databinding.FragmentPaymentsBinding

class ServicesFragment: Fragment() {

    private var _binding: FragmentPaymentsBinding? = null
    private val binding get() = _binding!!

    private lateinit var hostActivity: AppActivity
    private lateinit var recyclerViewClass: PaymentsRecyclerView

    private val servicesViewModel: ServicesViewModel by lazy {
        ViewModelProvider(this)[ServicesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentsBinding.inflate(inflater, container, false)

        recyclerViewClass = PaymentsRecyclerView(inflater, binding.recyclerView)

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        servicesViewModel.getServices().observe(this){
            recyclerViewClass.onStart(it)
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
        fun newInstance():ServicesFragment{
            return ServicesFragment()
        }
    }

}