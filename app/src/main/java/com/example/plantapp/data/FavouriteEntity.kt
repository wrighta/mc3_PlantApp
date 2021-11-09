package com.example.plantapp.data

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity (tableName = "favourites")
data class FavouriteEntity(
    @PrimaryKey val id: Int,
    var myNotes : String
)
