package com.example.pexelsvlad.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pexelsvlad.adapters.CollectionsAdapter
import com.example.pexelsvlad.adapters.CuratedPhotosAdapter
import com.example.pexelsvlad.databinding.FragmentCuratedPhotosBinding
import com.example.pexelsvlad.extensions.addSpaceDecoration
import com.example.pexelsvlad.extensions.configureActionBar
import com.example.pexelsvlad.fragments.delegates.viewBinding
import com.example.pexelsvlad.viewmodels.CuratedPhotosViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CuratedPhotosFragment : Fragment() {
    private val binding by viewBinding(FragmentCuratedPhotosBinding::inflate)
    private val viewModel: CuratedPhotosViewModel by viewModels()

    private val verticalAdapter = CuratedPhotosAdapter{
        findNavController().navigate(CuratedPhotosFragmentDirections.actionCuratedPhotosFragmentToPhotoDetailsFragment(it.id,it.photographer,true))
    }

    private val horizontalAdapter = CollectionsAdapter{

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        configureActionBar("",visible = false,hasBackButton = false)

        loadFeaturedCollections()
        loadCuratedPhotos()

        return binding.root
    }

    private fun loadCuratedPhotos(){
      with(binding){
          verticalRecyclerView.layoutManager = GridLayoutManager(requireContext(), COLUMNS_COUNT_VERTICAL)
          viewLifecycleOwner.lifecycleScope.launch {
              verticalRecyclerView.adapter=verticalAdapter
              viewModel.getAll().onEach {
                  verticalAdapter.submitList(it)
              }
                  .launchIn(viewLifecycleOwner.lifecycleScope)
          }
      }
    }

    private fun loadFeaturedCollections(){
        with(binding){
            horizontalRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            viewLifecycleOwner.lifecycleScope.launch {
                horizontalRecyclerView.adapter=horizontalAdapter
                horizontalRecyclerView.addSpaceDecoration(SPACE_SIZE)
                viewModel.getAllCollections().onEach {
                    horizontalAdapter.submitList(it)
                }
                    .launchIn(viewLifecycleOwner.lifecycleScope)
            }
        }
    }



    companion object {
        private const val COLUMNS_COUNT_VERTICAL = 2

        private const val SPACE_SIZE = 25
    }
}