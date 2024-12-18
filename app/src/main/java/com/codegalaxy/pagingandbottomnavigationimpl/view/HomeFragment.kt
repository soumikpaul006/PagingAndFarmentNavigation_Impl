package com.codegalaxy.pagingandbottomnavigationimpl.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.codegalaxy.pagingandbottomnavigationimpl.R
import com.codegalaxy.pagingandbottomnavigationimpl.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var backPressedTime:Long=0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment=childFragmentManager.findFragmentById(R.id.home_nav_host) as NavHostFragment
        val navController=navHostFragment.navController


        binding.bottomNavigationView.setupWithNavController(navController)

//        binding.bottomNavigationView.setOnItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.menu_list -> {
//                    navController.navigate(R.id.listFragment)
//                }
//                R.id.menu_profile -> {
//                    navController.navigate(R.id.profileFragment)
//                }
//            }
//            true
//        }

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val currentDestination = navController.currentDestination?.id
                    when (currentDestination) {
                        R.id.listFragment -> handleBackButton()
                        R.id.profileFragment -> navController.navigate(R.id.profileFragment)
                    }
                }
            })
    }





    private fun handleBackButton()
    {
        if(backPressedTime+1000>System.currentTimeMillis())
        {
            requireActivity().finish()
        }
        else{
            Toast.makeText(requireContext(),"Press back button twice", Toast.LENGTH_SHORT).show()
            backPressedTime=System.currentTimeMillis()
        }
    }
}