package com.example.todo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.todo.R
import com.example.todo.databinding.ActivityMainBinding
import com.example.todo.ui.sheetdialog.BottomSheetFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.my_nav_host) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.setupWithNavController(navController)
        binding.fab.setOnClickListener {
            BottomSheetFragment().show(supportFragmentManager,"")
        }

    }
}