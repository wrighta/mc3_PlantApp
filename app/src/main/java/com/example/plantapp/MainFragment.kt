package com.example.plantapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.plantapp.databinding.FragmentMainBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainFragment : Fragment(),
    PlantsListAdapter.ListItemListener{

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: PlantsListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.inflate(inflater, container, false)
        // remember viewModel contains the MutableLiveData which is the list of Plants
        // we will use this later to populate the RecyclerView and observe it for changes
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // with is used to access an object attributes, without have to refer to the object every time
        // with is actually a function, and the object in this case is the recyclerView
        with(binding.recyclerView) {
            setHasFixedSize(true)
            val divider = DividerItemDecoration(
                context, LinearLayoutManager(context).orientation
            )
            addItemDecoration(divider)
            // without using with you may have done this...
            // binding.recyclerView.addItemDecoration(divider)
        }

        viewModel.plantsList.observe(viewLifecycleOwner, Observer {
            // for debugging - Log.i() to the Logcat during execution and view Info messages with the tag TAG (see constants for the literal string)
            // Log.i(TAG, it.toString())

            adapter = PlantsListAdapter(it)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(activity)
         }    )

        return binding.root

    }



}