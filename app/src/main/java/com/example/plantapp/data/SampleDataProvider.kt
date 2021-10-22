package com.example.plantapp.data

import java.util.*

class SampleDataProvider {

    // Companion objects are is like creating static values in Java
    companion object {

        private val sampleName1 = "Monstera Deliciosa"
        private val sampleName2 = "Figus Layratha"
        private val sampleName3 = "Dracena"
        private val sampleName4 = "Fiddle Leaf Plant "

        private val cal = Calendar.getInstance()
        private val date = cal.set(20, 11, 22)

        private val sampleDesc1 = "2 Slips cut from main Monstera, rooted in water for 4 weeks"
        private val sampleDesc2 = "Propagation from leaves"
        private val sampleDesc3 = "2 Slips cut from main Monstera, rooted in water for 4 weeks"
        private val sampleDesc4 = "Propagation from leaves"


        private fun getDate(diff: Long): Date {
            return Date(Date().time + diff)
        }

      //  private fun setDate() :

        fun getPlants() = arrayListOf(
            PlantEntity(1, sampleName1, getDate(0), sampleDesc1),
            PlantEntity(2, sampleName2, getDate(1), sampleDesc2),
            PlantEntity(3, sampleName3, getDate(2), sampleDesc3),
            PlantEntity(4, sampleName4, getDate(3), sampleDesc4)

        )

    }
}