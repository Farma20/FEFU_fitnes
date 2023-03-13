package com.example.fefu_fitnes.UI.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fefu_fitnes.R
import com.example.fefu_fitnes.UI.RegisterPackage.UI.RegisterActivity
import com.example.fefu_fitnes.UI.ViewModels.MainViewModel
import com.example.fefu_fitnes.databinding.ActivityMainBinding

class AppActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val mainViewModel:MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //отключение темной темы
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if(currentFragment == null){
            val fragment = MainMenuFragment.newInstance()
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit()
        }

        binding.main.setBackgroundResource(R.drawable.activity_main_bottom_navigate_main_pick)

        binding.main.setOnClickListener {
            onFragmentSelected(MainMenuFragment.newInstance())
            it.setBackgroundResource(R.drawable.activity_main_bottom_navigate_main_pick)
            binding.tables.setBackgroundResource(R.drawable.activity_main_bottom_navigate_tables)
            binding.paid.setBackgroundResource(R.drawable.activity_main_bottom_navigate_paid)
            binding.profile.setBackgroundResource(R.drawable.activity_main_bottom_navigate_profile)
        }

        binding.tables.setOnClickListener {
            onFragmentSelected(TimetableFragment.newInstance())
            it.setBackgroundResource(R.drawable.activity_main_bottom_navigate_tables_pick)
            binding.main.setBackgroundResource(R.drawable.activity_main_bottom_navigate_main)
            binding.paid.setBackgroundResource(R.drawable.activity_main_bottom_navigate_paid)
            binding.profile.setBackgroundResource(R.drawable.activity_main_bottom_navigate_profile)
        }

        binding.paid.setOnClickListener {
            onFragmentSelected(ServicesFragment.newInstance())
            it.setBackgroundResource(R.drawable.activity_main_bottom_navigate_paid_pick)
            binding.main.setBackgroundResource(R.drawable.activity_main_bottom_navigate_main)
            binding.tables.setBackgroundResource(R.drawable.activity_main_bottom_navigate_tables)
            binding.profile.setBackgroundResource(R.drawable.activity_main_bottom_navigate_profile)
        }

        binding.profile.setOnClickListener {
            onFragmentSelected(ProfileFragment.newInstance())
            it.setBackgroundResource(R.drawable.activity_main_bottom_navigate_profile_pick)
            binding.main.setBackgroundResource(R.drawable.activity_main_bottom_navigate_main)
            binding.tables.setBackgroundResource(R.drawable.activity_main_bottom_navigate_tables)
            binding.paid.setBackgroundResource(R.drawable.activity_main_bottom_navigate_paid)
        }


    }

//    private fun checkInitialization(){
//        if(false){
//            val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
//
//            if(currentFragment == null){
//                val fragment = MainMenuFragment.newInstance()
//                supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit()
//            }
//        }
//        else{
//            val intent = Intent(this, RegisterActivity::class.java)
//            startActivity(intent)
//        }
//    }

    fun onFragmentSelected(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}