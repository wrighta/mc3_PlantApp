package com.example.plantapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plantapp.data.PlantEntity

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

        // action bar with the up/back/home button - Tick symbol, does not display in ListFragment, but is set to display in EditorFragment,
        (activity as AppCompatActivity)
            .supportActionBar?.setDisplayHomeAsUpEnabled(false)


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

            adapter = PlantsListAdapter(it, this@MainFragment)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(activity)
         }    )

        return binding.root

    }

    override fun onItemClick(plant: PlantEntity) {

        // Log - print out to logcat to help with debugging if errors occur
        // TAG is a constant defined in Constants.kt - you can search yhe logcat using this TAG to help with debugging errors
        Log.i(TAG, "onItemClick : Received Plant name ${plant.name}")
        val action = MainFragmentDirections.actionMainFragmentToEditorFragment(plant)
        findNavController().navigate(action)
    }



}