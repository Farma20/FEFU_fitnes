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
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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

        //текстЛистенеры
        binding.login.addTextChangedListener(commonTextWatcher(USER_EMAIL_TAG))

        binding.login.setOnFocusChangeListener{_, focused ->
            if (!focused){
                binding.loginLayout.helperText = validEmail()
            }
        }

        binding.password.addTextChangedListener(commonTextWatcher(USER_PASSWORD_TAG))

        binding.password.setOnFocusChangeListener{_, focused ->
            if (!focused){
                binding.passwordLayout.helperText = validPassword()
            }
        }

        binding.registrationButton.setOnClickListener{
            hostActivity.onFragmentSelected(RegistrationFragment())
        }

        binding.enterButton.setOnClickListener{
            binding.loginLayout.clearFocus()
            binding.passwordLayout.clearFocus()
            if (submitForm()){
                //вызов метода отправки данных на сервер у ViewModel
                Unit
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

    private fun commonTextWatcher(tag:String):TextWatcher{
        return object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if(tag == USER_EMAIL_TAG)
                    loginViewModel.setUserEmail(s.toString())
                if(tag == USER_PASSWORD_TAG)
                    loginViewModel.setUserPassword(s.toString())
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
            }
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