package com.example.pexelsvlad.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.pexelsvlad.R
import com.example.pexelsvlad.databinding.FragmentPhotoDetailsBinding
import com.example.pexelsvlad.domain.models.Photo
import com.example.pexelsvlad.extensions.configureActionBar
import com.example.pexelsvlad.fragments.delegates.viewBinding
import com.example.pexelsvlad.viewmodels.PhotoDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class PhotoDetailsFragment : Fragment() {
    private val binding by viewBinding(FragmentPhotoDetailsBinding::inflate)
    private val viewModel: PhotoDetailsViewModel by viewModels()

    private val args by navArgs<PhotoDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        configureActionBar(args.author, visible = true, hasBackButton = true)

        loadPhotoDetails(args.id)

        return binding.root
    }

    private fun loadPhotoDetails(id: String) {

        try {
            viewModel.getPhotoDetails(id)
                .onEach {
                    val photoDetails = it
                    with(binding) {
                        Glide.with(photoDetailsImageView.context)
                            .load(it.src?.portrait)
                            .into(photoDetailsImageView)
                        Glide.with(bookmarksImageView.context)
                            .load(isPhotoAddedToBookmarks(it))
                            .into(bookmarksImageView)

                        bookmarksImageView.setOnClickListener {
                            if (photoDetails.liked == false) {
                                viewModel.addToBookmarks(photoDetails)
                                Glide.with(bookmarksImageView.context)
                                    .load(isPhotoAddedToBookmarks(photoDetails))
                                    .into(bookmarksImageView)
                            } else {
                                viewModel.removeFromBookmarks(photoDetails.id)
                                Glide.with(bookmarksImageView.context)
                                    .load(isPhotoAddedToBookmarks(photoDetails))
                                    .into(bookmarksImageView)
                            }
                        }
                    }
                }
                .launchIn(viewLifecycleOwner.lifecycleScope)
        } catch (e: Throwable) {
            Toast.makeText(
                requireContext(),
                "Uups...Something goes wrong",
                Toast.LENGTH_LONG
            ).show()
        }

    }


    private fun isPhotoAddedToBookmarks(photo: Photo): Any {
        return if (photo.liked == false) {
            R.drawable.bookmark_button_inactive_small
        } else {
            R.drawable.bookmark_button_active
        }
    }

}
