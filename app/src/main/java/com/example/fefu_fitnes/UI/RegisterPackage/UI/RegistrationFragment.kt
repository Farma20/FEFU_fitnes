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

private const val USER_LOGIN_TAG = "userLogin"
private const val USER_PHONE_TAG = "userPhone"
private const val USER_EMAIL_TAG = "userEmail"
private const val USER_BIRTHDAY_TAG = "userBirthday"
private const val USER_PASSWORD_TAG = "userPassword"

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
        binding.login.addTextChangedListener(commonTextWatcher(USER_LOGIN_TAG))
        binding.login.setOnFocusChangeListener{_, focused ->
                if (!focused){
                    binding.loginLayout.helperText = validLogin()
                }
            }
        binding.phone.addTextChangedListener(commonTextWatcher(USER_PHONE_TAG))
        binding.phone.setOnFocusChangeListener{_, focused ->
            if (!focused){
                binding.phoneLayout.helperText = validPhone()
            }
        }
        binding.mail.addTextChangedListener(commonTextWatcher(USER_EMAIL_TAG))
        binding.mail.setOnFocusChangeListener{_, focused ->
            if (!focused){
                binding.mailLayout.helperText = validEmail()
            }
        }
        binding.birthday.addTextChangedListener(commonTextWatcher(USER_BIRTHDAY_TAG))
        binding.birthday.setOnFocusChangeListener{_, focused ->
            if (!focused){
                binding.birthdayLayout.helperText = validBirthday()
            }
        }
        binding.newPasswords.addTextChangedListener(commonTextWatcher(USER_PASSWORD_TAG))
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
            binding.loginLayout.clearFocus()
            binding.newPasswordsLayout.clearFocus()
            binding.repeatPasswordsLayout.clearFocus()
            binding.birthdayLayout.clearFocus()
            binding.mailLayout.clearFocus()
            binding.phoneLayout.clearFocus()
            if (submitForm()){
                Toast.makeText(
                    this.context,
                    "Вы успешно заригестрированны",
                    Toast.LENGTH_LONG
                ).show()
                registerViewModel.pushUserData()
            }
        }

    }

    private fun submitForm(): Boolean {
        if( binding.loginLayout.helperText == null &&
            binding.newPasswordsLayout.helperText == null &&
            binding.repeatPasswordsLayout.helperText == null &&
            binding.birthdayLayout.helperText == null &&
            binding.mailLayout.helperText == null &&
            binding.phoneLayout.helperText == null){

            if( registerViewModel.getUserLogin()!="" &&
                registerViewModel.getUserPassword()!=""&&
                registerViewModel.getUserBirthday()!=""&&
                registerViewModel.getUserEmail()!=""&&
                registerViewModel.getUserPhoneNumber()!=""){

                return true
            }
            else{
                println("${registerViewModel.getUserLogin()}," +
                        " ${registerViewModel.getUserPassword()},"+
                        "${registerViewModel.getUserBirthday()},"+
                        "${registerViewModel.getUserEmail()},"+
                        "${registerViewModel.getUserPhoneNumber()}")
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

    private fun commonTextWatcher(tag:String):TextWatcher{
        return object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if(tag == USER_LOGIN_TAG)
                    registerViewModel.setUserLogin(s.toString())
                if(tag == USER_PHONE_TAG)
                    registerViewModel.setUserPhoneNumber(s.toString())
                if(tag == USER_EMAIL_TAG)
                    registerViewModel.setUserEmail(s.toString())
                if(tag == USER_BIRTHDAY_TAG)
                    registerViewModel.setUserBirthday(s.toString())
                if(tag == USER_PASSWORD_TAG)
                    registerViewModel.setUserPassword(s.toString())
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
        fun newInstance(): RegistrationFragment {
            return RegistrationFragment()
        }
    }
}