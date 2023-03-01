package com.example.fefu_fitnes.UI.Views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fefu_fitnes.R
import com.example.fefu_fitnes.UI.Models.UserDataModel
import com.example.fefu_fitnes.UI.ViewModels.MainViewModel
import com.example.fefu_fitnes.data.Repository.MainRepository
import com.example.fefu_fitnes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val mainViewModel:MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    //костыльно создаю главный репозиторий (перерделать в дальнейшем)
    private val mainRepository = MainRepository.getInstance()

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

    fun onFragmentSelected(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}