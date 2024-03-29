package com.example.fefu_fitnes.UI.RegisterPackage.UI

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fefu_fitnes.UI.RegisterPackage.Repository.RegisterRepository
import com.example.fefu_fitnes.UI.RegisterPackage.ViewModels.LoginViewModel
import com.example.fefu_fitnes.databinding.FragmentLoginBinding

private const val USER_EMAIL_TAG = "userEmail"
private const val USER_PASSWORD_TAG = "userPassword"

class LoginFragment: Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var  hostActivity: RegisterActivity
    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater)

        return binding.root
    }

    private fun submitForm(): Boolean {
        return binding.loginLayout.helperText == null && binding.passwordLayout.helperText == null
    }


    override fun onStart() {
        super.onStart()

        binding.login.setOnFocusChangeListener{_, focused ->
            if (!focused){
                binding.loginLayout.helperText = validEmail()
            }
        }

        binding.password.setOnFocusChangeListener{_, focused ->
            if (!focused){
                binding.passwordLayout.helperText = validPassword()
            }
        }

        binding.registrationButton.setOnClickListener{
            hostActivity.onFragmentSelected(RegistrationFragment())
        }

        binding.enterButton.setOnClickListener{
//            enterButtonValidate()
            loginViewModel.setUserEmail(binding.login.text.toString())
            loginViewModel.setUserPassword(binding.password.text.toString())

            println(loginViewModel.getUserEmail())
            println(loginViewModel.getUserPassword())
            loginViewModel.pushLoginData()
        }
    }

    private fun enterButtonValidate(){
        binding.loginLayout.clearFocus()
        binding.passwordLayout.clearFocus()
        if (submitForm()){
            loginViewModel.setUserEmail(binding.login.text.toString())
            loginViewModel.setUserPassword(binding.password.text.toString())
            if(loginViewModel.validateLoginData()){
                Toast.makeText(
                    this.context,
                    "Вы вошли",
                    Toast.LENGTH_LONG
                ).show()
                RegisterRepository.setUserInit(true)
            }
            else{
                Toast.makeText(
                    this.context,
                    "Такого пользователя не найденно, измените введенные данные или зарегистрируйтесь",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun validPassword(): String? {
        val passwordText = binding.password.text.toString()
        if (passwordText.length < 8){
            return "Минимальный пароль 8 символов"
        }
        if(passwordText.matches(".*[а-я].*".toRegex())){
            return "Пароль может содержать только латинские буквы, цифры и сиволы"
        }
        if(!passwordText.matches(".*[a-z].*".toRegex())){
            return "Пароль должен содержать хотябы одну латинскую букву"
        }
        return null
    }

    private fun validEmail(): String? {

        val emailText = binding.login.text.toString()
        if(!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()){
            return "Недопустимый адрес почты"
        }

        return null
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