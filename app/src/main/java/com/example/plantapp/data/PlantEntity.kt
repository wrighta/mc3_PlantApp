package com.example.plantapp.data

import com.example.plantapp.NEW_PLANT_ID
import java.util.*

// data means the class is going to have some properties, will have at least one primary constructor, and have functions such as equals() toString...
// See https://www.javatpoint.com/kotlin-data-class for a comparison of Java and Kotlin classes
data class PlantEntity(
    var id: Int,
    var name: String,
    var date: Date,
    var description: String
)
{
    // no arguments constructor - if no values are passed in this one is executed.
    constructor() : this(NEW_PLANT_ID, "", Date(), "")
    // New Plant - this constructor is called when ther eis a name, date and description, but no plant ID yet
    constructor(name: String, date: Date, description: String) : this(NEW_PLANT_ID, name, date, description)
}


//import android.os.Parcelable
////import androidx.room.Entity
////import androidx.room.PrimaryKey
//import com.example.plantapp.NEW_PLANT_ID
//import kotlinx.android.parcel.Parcelize
//import java.util.*
//
//@Parcelize
//@Entity(tableName = "plants")
//data class PlantEntity(
//    @PrimaryKey(autoGenerate = true)
//    var id: Int,
//    var name: String,
//    var date: Date,
//    var description: String
//
//
//
//) : Parcelable {
//    constructor() : this(NEW_PLANT_ID, "", Date(), "")
//    constructor(name: String, date: Date, description: String) : this(NEW_PLANT_ID, name, date, description)
//}

