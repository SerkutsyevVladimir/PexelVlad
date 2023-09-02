package com.example.pexelsvlad.fragments


import android.content.ContentValues
import android.content.ContentValues.TAG
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.util.Log
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
import com.example.pexelsvlad.extensions.configureActionBar
import com.example.pexelsvlad.fragments.delegates.viewBinding
import com.example.pexelsvlad.viewmodels.PhotoDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.concurrent.Executors

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
        configureActionBar("", visible = false, hasBackButton = false)

        loadPhotoDetails(args.id)

        var bitmapPhoto: Bitmap?

        val webPath = "https://images.pexels.com/photos/17978629/pexels-photo-17978629.jpeg"

        val myExecutor = Executors.newSingleThreadExecutor()
        val myHandler = Handler(Looper.getMainLooper())

        binding.downloadButton.setOnClickListener{

            viewLifecycleOwner.lifecycleScope.launch {
                val bitmap = mLoad(webPath)
                saveBitmapImage(bitmap)
                Toast.makeText(requireContext(), "Saved...TESTTESTTEST", Toast.LENGTH_SHORT).show()
            }

        }

        return binding.root
    }

    private fun loadPhotoDetails(id: String) {

        try {
            viewModel.getPhotoDetails(id)
                .onEach {
                    with(binding) {
                        Glide.with(photoDetailsImageView.context)
                            .load(it.src?.portrait)
                            .into(photoDetailsImageView)
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

//    fun downloadImage(imageURL: String) {
//        try {
//            val image = BitmapFactory.decodeStream(imageURL.getInp)
//        } catch (e: IOException) {
//            println(e)
//        }
//
//
//    }

    // Function to establish connection and load image
    suspend fun mLoad(string: String): Bitmap = withContext(Dispatchers.IO){
        val url: URL = mStringToURL(string)!!
        val connection: HttpURLConnection?

            connection = url.openConnection() as HttpURLConnection
            connection.connect()
            val inputStream: InputStream = connection.inputStream
            val bufferedInputStream = BufferedInputStream(inputStream)
            return@withContext BitmapFactory.decodeStream(bufferedInputStream)

    }

    // Function to convert string to URL
    suspend fun mStringToURL(string: String): URL? = withContext(Dispatchers.IO){
        try {
            return@withContext URL(string)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        return@withContext null
    }

    suspend fun saveBitmapImage(bitmap: Bitmap) = withContext(Dispatchers.IO) {
        val timestamp = System.currentTimeMillis()

        //Tell the media scanner about the new file so that it is immediately available to the user.
        val values = ContentValues()
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/png")
        values.put(MediaStore.Images.Media.DATE_ADDED, timestamp)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            values.put(MediaStore.Images.Media.DATE_TAKEN, timestamp)
            values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/" + getString(R.string.app_name))
            values.put(MediaStore.Images.Media.IS_PENDING, true)
            val uri = requireContext().contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            if (uri != null) {
                try {
                    val outputStream = requireContext().contentResolver.openOutputStream(uri)
                    if (outputStream != null) {
                        try {
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                            outputStream.close()
                        } catch (e: Exception) {
                            Log.e(TAG, "saveBitmapImage: ", e)
                        }
                    }
                    values.put(MediaStore.Images.Media.IS_PENDING, false)
                    requireContext().contentResolver.update(uri, values, null, null)

                    Toast.makeText(requireContext(), "Saved...", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Log.e(TAG, "saveBitmapImage: ", e)
                }
            }else{

            }
        } else {
            val imageFileFolder = File(Environment.getExternalStorageDirectory().toString() + '/' + getString(R.string.app_name))
            if (!imageFileFolder.exists()) {
                imageFileFolder.mkdirs()
            }
            val mImageName = "$timestamp.png"
            val imageFile = File(imageFileFolder, mImageName)
            try {
                val outputStream: OutputStream = FileOutputStream(imageFile)
                try {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                    outputStream.close()
                } catch (e: Exception) {
                    Log.e(TAG, "saveBitmapImage: ", e)
                }
                values.put(MediaStore.Images.Media.DATA, imageFile.absolutePath)
                requireContext().contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

                Toast.makeText(requireContext(), "Saved...", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Log.e(TAG, "saveBitmapImage: ", e)
            }
        }
    }

}

//    private fun mSaveMediaToStorage(bitmap: Bitmap?) {
//        val filename = "${System.currentTimeMillis()}.jpg"
//        var fos: OutputStream? = null
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            this.contentResolver?.also { resolver ->
//                val contentValues = ContentValues().apply {
//                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
//                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
//                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
//                }
//                val imageUri: Uri? = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
//                fos = imageUri?.let { resolver.openOutputStream(it) }
//            }
//        } else {
//            val imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
//            val image = File(imagesDir, filename)
//            fos = FileOutputStream(image)
//        }
//        fos?.use {
//            bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, it)
//            Toast.makeText(this , "Saved to Gallery" , Toast.LENGTH_SHORT).show()
//        }
//    }
//}
//}