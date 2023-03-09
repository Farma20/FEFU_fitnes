package com.example.fefu_fitnes.UI.Register.UI

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fefu_fitnes.databinding.FragmentLoginBinding

class LoginFragment: Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var  hostActivity: RegisterActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater)

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.registrationButton.setOnClickListener{
            hostActivity.onFragmentSelected(RegistrationFragment())
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        hostActivity = context as RegisterActivity
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object{
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }
}