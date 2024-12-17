package com.codegalaxy.pagingandbottomnavigationimpl.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.codegalaxy.pagingandbottomnavigationimpl.R
import com.codegalaxy.pagingandbottomnavigationimpl.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var backPressedTime:Long=0

    private var currentFragment:String="ListFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        replaceFragment(ListFragment(),"ListFragment")//initial loads the list fragment

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_list -> {
                    replaceFragment(ListFragment(),"ListFragment")
                    currentFragment="ListFragment"
                }
                R.id.menu_profile -> {
                    replaceFragment(ProfileFragment(),"ProfileFragment")
                    currentFragment="ProfileFragment"
                }
            }
            true
        }

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {

                override fun handleOnBackPressed() {
                    if(currentFragment=="ListFragment")
                    {
                        handleBackButton()
                    }
                    else if(currentFragment=="ProfileFragment"){
                        replaceFragment(ListFragment(),"ListFragment")
                        currentFragment="ListFragment"
                    }
                }
            })
    }

    private fun replaceFragment(fragment: Fragment,tag:String) {

        childFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view, fragment,tag)
            .commit()
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