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
    // Glide uses this get method to get the image from the web using the URL
//    val thumbnailImageUrl
//        get() = imageName

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(imageName)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeDouble(price)
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

