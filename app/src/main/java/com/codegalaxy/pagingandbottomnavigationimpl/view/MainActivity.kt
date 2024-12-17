package com.codegalaxy.pagingandbottomnavigationimpl.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codegalaxy.pagingandbottomnavigationimpl.R
import com.codegalaxy.pagingandbottomnavigationimpl.databinding.ActivityMainBinding

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, SplashFragment())
                .commit()
        }
    }
}
