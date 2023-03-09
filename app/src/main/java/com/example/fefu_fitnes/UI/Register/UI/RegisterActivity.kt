package com.example.fefu_fitnes.UI.Register.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.fefu_fitnes.R
import com.example.fefu_fitnes.databinding.ActivityRegisterBinding

class RegisterActivity:AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    override fun onStart() {
        super.onStart()

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_register)

        if(currentFragment == null){
            val fragment = LoginFragment.newInstance()
            supportFragmentManager.beginTransaction().add(R.id.fragment_container_register, fragment).commit()
        }
    }


    fun onFragmentSelected(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_register, fragment)
            .addToBackStack(null)
            .commit()
    }
}