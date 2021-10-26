package com.example.plantapp

import android.util.Log
import androidx.lifecycle.*
import com.example.plantapp.data.PlantEntity
import com.example.plantapp.dataaccess.RetrofitInstance
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    // MutableLiveData - means this list can be changed at runtime
    // Note!!! _plants above is private, only visible here the underscore represents variables not exposed to the UI layer (fragments)
    private val _plants: MutableLiveData<List<PlantEntity>> = MutableLiveData()

    // Plants is exposed to the UI - Fragment
    val plants: LiveData<List<PlantEntity>>
        // get() This is a getter() function, which returns the list of plants as LiveData
        get() = _plants

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading


    // No Longer get the data from SampleDataProvider
    init {
//        // here we get the plant list data to share with the user interface
          getPlants()
    }

    private fun getPlants() {
        // web-access so run in a background thread - Coroutine
        viewModelScope.launch {
            _isLoading.value = true
            val fetchedPlants = RetrofitInstance.api.getPlants()
            Log.i(TAG, "List of Plants : $fetchedPlants")
            _plants.value = fetchedPlants
            _isLoading.value = false
        }
    }
}