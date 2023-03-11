package com.example.fefu_fitnes.UI.RegisterPackage.UI

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fefu_fitnes.R
import com.example.fefu_fitnes.UI.RegisterPackage.ViewModels.RegisterViewModel
import com.example.fefu_fitnes.databinding.FragmentRegisterBinding

class RegistrationFragment: Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private lateinit var  hostActivity: RegisterActivity
    private val registerViewModel: RegisterViewModel by lazy {
        ViewModelProvider(this)[RegisterViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater)

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.registrationRadioMale.isChecked = true

        //ТекстЛистенеры
        binding.login.setOnFocusChangeListener{_, focused ->
                if (!focused){
                    binding.loginLayout.helperText = validLogin()
                }
            }
        binding.phone.setOnFocusChangeListener{_, focused ->
            if (!focused){
                binding.phoneLayout.helperText = validPhone()
            }
        }
        binding.mail.setOnFocusChangeListener{_, focused ->
            if (!focused){
                binding.mailLayout.helperText = validEmail()
            }
        }
        binding.birthday.setOnFocusChangeListener{_, focused ->
            if (!focused){
                binding.birthdayLayout.helperText = validBirthday()
            }
        }
        binding.newPasswords.setOnFocusChangeListener{_, focused ->
            if (!focused){
                binding.newPasswordsLayout.helperText = validPassword()
            }
        }
        binding.gender.setOnCheckedChangeListener{_, checkedId ->
            val gender:String = if(checkedId == R.id.registration_radio_male){
                "М"
            }else{
                "Ж"
            }
            view?.findViewById<RadioButton>(checkedId).apply {
                this?.let { registerViewModel.setUserGender(gender) }
            }
        }

        binding.repeatPasswords.setOnFocusChangeListener{_, focused ->
            if (!focused){
                binding.repeatPasswordsLayout.helperText = validateRepeatPasswords()
            }
        }

        binding.registrationButton.setOnClickListener{
            registrationButtonSubmit()

        }

    }

    private fun registrationButtonSubmit(){
        binding.loginLayout.clearFocus()
        binding.newPasswordsLayout.clearFocus()
        binding.repeatPasswordsLayout.clearFocus()
        binding.birthdayLayout.clearFocus()
        binding.mailLayout.clearFocus()
        binding.phoneLayout.clearFocus()
        if (submitForm()){
            pushDataInViewModel()
            Toast.makeText(
                this.context,
                "Вы успешно заригестрированны",
                Toast.LENGTH_LONG
            ).show()
            registerViewModel.pushUserData()
        }
    }

    private fun pushDataInViewModel(){
        registerViewModel.setUserLogin(binding.login.text.toString())
        registerViewModel.setUserPhoneNumber(binding.phone.text.toString())
        registerViewModel.setUserEmail(binding.mail.text.toString())
        registerViewModel.setUserBirthday(binding.birthday.text.toString())
        registerViewModel.setUserPassword(binding.newPasswords.text.toString())

        println("${registerViewModel.getUserLogin()} ${registerViewModel.getUserPhoneNumber()} "+
            "${registerViewModel.getUserEmail()} ${registerViewModel.getUserBirthday()} ${registerViewModel.getUserPassword()}")
    }

    private fun submitForm(): Boolean {
        if( binding.loginLayout.helperText == null &&
            binding.newPasswordsLayout.helperText == null &&
            binding.repeatPasswordsLayout.helperText == null &&
            binding.birthdayLayout.helperText == null &&
            binding.mailLayout.helperText == null &&
            binding.phoneLayout.helperText == null){

            if( binding.login.text.toString() !="" &&
                binding.newPasswords.text.toString()!=""&&
                binding.birthday.text.toString()!=""&&
                binding.mail.text.toString()!=""&&
                binding.phone.text.toString()!=""){

                return true
            }
            else{

                Toast.makeText(
                    this.context,
                    "Все поля должны быть заполнены",
                    Toast.LENGTH_LONG
                ).show()
                return false
            }
        }

        Toast.makeText(
            this.context,
            "Заполните корректно все поля",
            Toast.LENGTH_LONG
        ).show()

        return false
    }

    private fun validateRepeatPasswords():String? {
        val repeatPasswordText = binding.repeatPasswords.text.toString()
        val passwordText = binding.newPasswords.text.toString()
        if (repeatPasswordText != passwordText)
            return "Пароли не совпадают"
        return null
    }

    private fun validBirthday(): String? {
        val birthdayText = binding.birthday.text.toString()
        if (birthdayText.length < 10)
            return "Неверный формат даты. Введите дату в формате дд.мм.гггг"
        if (birthdayText[2] != '.' || birthdayText[5] != '.')
            return "Неверный формат даты. День, месяц и год должны быть разделены точками '.'"

        return null
    }

    private fun validPhone(): String? {
        val phoneText = binding.phone.text.toString()
        if (phoneText.length != 12){
            return "Номер телефона должен состоять из 11 символов, не учитывая символ '+'"
        }

        return null
    }

    private fun validLogin(): String? {
        val loginText = binding.login.text.toString()
        if (loginText.length < 4){
            return "Минимальный логин 4 символа"
        }

        return null
    }

    private fun validPassword(): String? {
        val passwordText = binding.newPasswords.text.toString()
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

        val emailText = binding.mail.text.toString()
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
        fun newInstance(): RegistrationFragment {
            return RegistrationFragment()
        }
    }
}