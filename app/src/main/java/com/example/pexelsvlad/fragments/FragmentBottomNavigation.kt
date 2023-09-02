package com.example.pexelsvlad.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.pexelsvlad.R
import com.example.pexelsvlad.databinding.FragmentBottomNavigationBinding
import com.example.pexelsvlad.extensions.configureActionBar
import com.example.pexelsvlad.fragments.delegates.viewBinding

class FragmentBottomNavigation : Fragment() {
    private val binding by viewBinding(FragmentBottomNavigationBinding::inflate)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        configureActionBar("", visible = false, hasBackButton = false)

        with(binding) {
            val nestedController =
                (childFragmentManager.findFragmentById(R.id.page_container) as NavHostFragment)
                    .navController
            bottomNavigation.setupWithNavController(nestedController)
        }

        return binding.root
    }
}