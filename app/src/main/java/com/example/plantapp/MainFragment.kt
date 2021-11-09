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
        setHasOptionsMenu(true)


        binding = FragmentMainBinding.inflate(inflater, container, false)
        // remember viewModel contains the LiveData which is the list of Plants
        // Instantiate the ViewModel - init in the view model will be called first time this is done.
        // Have a look at ViewModel init{ } it calls getPlants() which gets the list of plants from the API using retrofit
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // with is used to access an object attributes, without have to refer to the object every time
        // with is actually a function, and the object in this case is the recyclerView
        with(binding.recyclerView) {
            setHasFixedSize(true)
            val divider = DividerItemDecoration(
                context, LinearLayoutManager(context).orientation
            )
            addItemDecoration(divider)
        }

        viewModel.plants.observe(viewLifecycleOwner, Observer {
            adapter = PlantsListAdapter(it, this@MainFragment)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(activity)
         }    )

        return binding.root

    }

    override fun onItemClick(plant: PlantEntity) {

        // Log - print out to logcat to help with debugging if errors occur
        // TAG is a constant defined in Constants.kt - you can search yhe logcat using this TAG to help with debugging errors
        // Log.i(TAG, "onItemClick : Received Plant name ${plant.name}")
        val action = MainFragmentDirections.actionMainFragmentToEditorFragment(plant)
        findNavController().navigate(action)
    }



}