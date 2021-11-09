package com.example.plantapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantapp.data.FavouriteEntity
import com.example.plantapp.localDB.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditorViewModel (app: Application) : AndroidViewModel(app) {
    private val database = AppDatabase.getInstance(app)
    val currentFavourite = MutableLiveData<FavouriteEntity>()

    fun getFavourite(favouriteId: Int) {
        Log.i(TAG2, "Id : " + favouriteId)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val favourite =
                    database?.favouriteDao()?.getFavouriteById(favouriteId)

                favourite?.let {
                    currentFavourite.postValue(it)
                    Log.i(TAG2, "MyNotes Returned from DB" + it.myNotes)
                }
            }
        }
    }

    fun saveFavourite(favouriteEntity: FavouriteEntity) {

        viewModelScope.launch {
            withContext(Dispatchers.IO){
               database?.favouriteDao()?.insertFavourite(favouriteEntity)
            }
        }
    }


}