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
import com.example.pexelsvlad.adapters.BookmarksAdapter
import com.example.pexelsvlad.databinding.FragmentBookmarksBinding
import com.example.pexelsvlad.extensions.configureActionBar
import com.example.pexelsvlad.fragments.delegates.viewBinding
import com.example.pexelsvlad.viewmodels.BookmarksViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookmarksFragment : Fragment() {
    private val binding by viewBinding(FragmentBookmarksBinding::inflate)
    private val viewModel: BookmarksViewModel by viewModels()

    private val adapter = BookmarksAdapter {
        findNavController().navigate(
            BookmarksFragmentDirections.actionBookmarksFragmentToPhotoDetailsFragment(
                it.id,
                it.photographer,
                false
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        configureActionBar("Bookmarks", visible = true, hasBackButton = false)

        loadBookmarks()

        return binding.root
    }

    private fun loadBookmarks(){
        with(binding){
            verticalRecyclerView.layoutManager = GridLayoutManager(requireContext(), COLUMNS_COUNT_VERTICAL)
            viewLifecycleOwner.lifecycleScope.launch {
                verticalRecyclerView.adapter=adapter
                viewModel.getAll().onEach {
                    adapter.submitList(it)
                }
                    .launchIn(viewLifecycleOwner.lifecycleScope)
            }
        }
    }


    companion object {
        private const val COLUMNS_COUNT_VERTICAL = 2
    }
}