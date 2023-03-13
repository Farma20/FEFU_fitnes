package com.example.fefu_fitnes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fefu_fitnes.UI.RegisterPackage.Repository.RegisterRepository
import com.example.fefu_fitnes.UI.RegisterPackage.UI.RegisterActivity
import com.example.fefu_fitnes.UI.Views.AppActivity

private const val APP_ACTIVITY = "appActivity"
private const val REGISTER_ACTIVITY = "registerActivity"

class MainMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_main)

        RegisterRepository.getUserInit().observe(this){
            if (it == true)
                setActivity(APP_ACTIVITY)
            else
                setActivity(REGISTER_ACTIVITY)
        }

    }

    private fun setActivity(activityName:String){
        if(activityName == APP_ACTIVITY){
            val intent = Intent(this, AppActivity::class.java)
            startActivity(intent)
        }
        if(activityName == REGISTER_ACTIVITY){
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}