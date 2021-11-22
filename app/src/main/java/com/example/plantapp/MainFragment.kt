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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plantapp.data.PlantEntity

import com.example.plantapp.databinding.FragmentMainBinding


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
           viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        viewModel.plants.observe(viewLifecycleOwner, Observer {
            // Pass the context into the adapter as Glide needs to know it
            adapter = PlantsListAdapter(requireContext(),it, this@MainFragment)
            binding.recyclerView.adapter = adapter
           // binding.recyclerView.layoutManager = GridLayoutManager(activity) // no need for this...LayoutManager decided in the xml
         }    )

        return binding.root

    }

    override fun onItemClick(plant: PlantEntity) {
   val action = MainFragmentDirections.actionMainFragmentToEditorFragment(plant)
        findNavController().navigate(action)
    }



}