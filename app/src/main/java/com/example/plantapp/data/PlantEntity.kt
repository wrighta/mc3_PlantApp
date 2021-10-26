package com.example.plantapp.data

import android.os.Parcel
import com.example.plantapp.NEW_PLANT_ID
import java.util.*
import android.os.Parcelable

// data means the class is going to have some properties, will have at least one primary constructor, and have functions such as equals() toString...
// See https://www.javatpoint.com/kotlin-data-class for a comparison of Java and Kotlin classes

data class PlantEntity(
    // Note in this version of my code I've changed the attributes so they match up with the JSON data.
    var id: Int,
    var imageName: String?,
    var name: String?,
    var description: String?,
    var price: Double
) : Parcelable
{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble()
    ) {
    }

    // Don't think I need this constructor....no arguments constructor - if no values are passed in this one is executed.
   // constructor() : this(NEW_PLANT_ID, "", "", "", 0.0)
    // New Plant - this constructor is called when there is a name, date and description, but no plant ID yet
   // constructor(name: String, date: Date, description: String) : this(NEW_PLANT_ID, name, description)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PlantEntity> {
        override fun createFromParcel(parcel: Parcel): PlantEntity {
            return PlantEntity(parcel)
        }

        override fun newArray(size: Int): Array<PlantEntity?> {
            return arrayOfNulls(size)
        }
    }
}

