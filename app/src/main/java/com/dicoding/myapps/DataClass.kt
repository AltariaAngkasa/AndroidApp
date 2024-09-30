package com.dicoding.myapps

import android.os.Parcel
import android.os.Parcelable

data class DataClass(
    var dataImage: Int,
    var dataTitle: String,
    var dataDesc: String,
    var dataDetailImage: Int,
    var distance: String, // Jarak dari Bumi
    var numberOfMoons: String, // Jumlah bulan
    var size: String, // Ukuran planet
    var orbitPeriod: String // Periode orbit
) : Parcelable {
    constructor(parcel: Parcel) : this(
        dataImage = parcel.readInt(),
        dataTitle = parcel.readString() ?: "",
        dataDesc = parcel.readString() ?: "",
        dataDetailImage = parcel.readInt(),
        distance = parcel.readString() ?: "",
        numberOfMoons = parcel.readString() ?: "",
        size = parcel.readString() ?: "",
        orbitPeriod = parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(dataImage)
        parcel.writeString(dataTitle)
        parcel.writeString(dataDesc)
        parcel.writeInt(dataDetailImage)
        parcel.writeString(distance)
        parcel.writeString(numberOfMoons)
        parcel.writeString(size)
        parcel.writeString(orbitPeriod)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DataClass> {
        override fun createFromParcel(parcel: Parcel): DataClass {
            return DataClass(parcel)
        }

        override fun newArray(size: Int): Array<DataClass?> {
            return arrayOfNulls(size)
        }
    }
}
