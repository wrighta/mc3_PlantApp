package com.example.plantapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.plantapp.data.PlantEntity
import com.example.plantapp.databinding.ListItemBinding
import android.content.Context


// a reference to the Plant List data is passed in during intialisation
class PlantsListAdapter(val context: Context,
    private val plantsList: List<PlantEntity>,
    private val listener: ListItemListener) :

    RecyclerView.Adapter<PlantsListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        // binding to list_item.xml
        val binding = ListItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        // list_item is a layout file, ctrl/cmd click will bring you to this file
        // you must create list_item.xml and design it as you want for one list item
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = plantsList.size

    // each time data is passed to the RecyclerView's row you need to bind the data to that ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val plant = plantsList[position]
        with(holder.binding) {
            plantName.text = plant.name
            // load the image from the web(imageName)
            // into the plantImage object in the layout
                Glide.with(context)
                    .load(plant.imageName)
                    .into(plantImage)
            plantImage.setOnClickListener{
                listener.onItemClick(plant)
            }}}

    interface ListItemListener {
        fun onItemClick(plant: PlantEntity)
    }
}