package com.nasagallery.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nasagallery.R
import com.nasagallery.databinding.FragmentNasaItemsBinding
import com.nasagallery.model.local.NASAPhotoItem
import com.nasagallery.model.local.NASAPhotos
import com.nasagallery.view.adapter.NasaItemAdapter
import com.nasagallery.view.adapter.NasaItemCallBack
import com.nasagallery.viewmodel.NasaItemViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "NasaItemsFragment"

@AndroidEntryPoint
class NasaItemsFragment : Fragment(), NasaItemCallBack {

    private lateinit var binding: FragmentNasaItemsBinding
    private lateinit var nasaViewModel: NasaItemViewModel
    private var nasaItemAdapter: NasaItemAdapter? = null
    private var nasaItemList: ArrayList<NASAPhotoItem>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nasaItemAdapter = NasaItemAdapter(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_nasa_items,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "onViewCreated: ")

        nasaViewModel = ViewModelProvider(requireActivity())[NasaItemViewModel::class.java]

        nasaViewModel.getPhoto().observe(viewLifecycleOwner) { it ->
            Log.d(TAG, "getPhotos: $it")
            nasaItemList = it
            it.reverse()
            setUpAdapter(it)
        }
    }

    private fun setUpAdapter(it: NASAPhotos?) {
        binding.layoutLoading.constraintLoading.visibility = View.GONE
        if (it != null) {
            nasaItemAdapter?.addData(it)
            binding.recyclerPhoto.adapter = nasaItemAdapter

            binding.recyclerPhoto.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }

    override fun goToDetailPage(title: String) {
        for (item in nasaItemList!!) {
            if (title == item.title) {
                nasaViewModel.photoItem.value = item
                findNavController().navigate(R.id.nasaItemDetailsFragment)
                break
            }
        }
    }

}