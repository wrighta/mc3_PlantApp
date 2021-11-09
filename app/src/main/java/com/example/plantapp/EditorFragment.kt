package com.example.plantapp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.plantapp.data.FavouriteEntity
import com.example.plantapp.databinding.EditorFragmentBinding

class EditorFragment : Fragment() {

    private val args: EditorFragmentArgs by navArgs()
    private lateinit var binding: EditorFragmentBinding
    private lateinit var viewModel: EditorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // ActionBar is menu on top
        (activity as AppCompatActivity).supportActionBar?.let {
            // 'it' is similar to 'this' in Java (there are small differences)
            it.setHomeButtonEnabled(true)
            it.setDisplayShowHomeEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_check)
        }
        setHasOptionsMenu(true)



        binding = EditorFragmentBinding.inflate(inflater, container, false)

        // Set the title and description from the Plant object passed in from the MainFragment
        binding.title.setText(args.plant.name)
        binding.description.setText(args.plant.description)

        // create the viewModel, observe the live data (Favourite object for the current Plant)
        // if the live data changes update the layout so it displays those comments.
        viewModel = ViewModelProvider(this).get(EditorViewModel::class.java)
        viewModel.currentFavourite.observe(viewLifecycleOwner, Observer {
           binding.myNotes.setText(it.myNotes)
        })

        // tell the viewModel to get access the local database to see if there are favourite comments for the current plant
        viewModel.getFavourite(args.plant.id)


        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true){
                override fun handleOnBackPressed(){
                    // you write the code for saveAndReturn - later this will need to save to the Database
                    saveAndReturn()
                }
            }
        )
        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            // When the home button is clicked, save changes then return to the MainFragment, which is the List
            android.R.id.home -> saveAndReturn()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveAndReturn() : Boolean{
        // at the moment we save to favourites, even if there are no comments
        // Try insert a save or cancel functionality so this does not happen.
        viewModel.saveFavourite(FavouriteEntity(args.plant.id, binding.myNotes.text.toString()))

        findNavController().navigateUp()
        return true
    }



}