package com.dester.androidcourse2020github

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@SuppressLint("ParcelCreator")
data class Game(val name:String, val author:String, val about:String, val rating:Float, val id:Int):
     Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "Default",
        parcel.readString() ?: "Default",
        parcel.readString() ?: "Default",
        parcel.readFloat(),
        parcel.readInt()
    ) {
    }

    override fun equals(other: Any?): Boolean {
        if(other is Game){
            return (name == other.name && about == other.about && id == other.id)
        }
        return false
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(author)
        parcel.writeString(about)
        parcel.writeFloat(rating)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Game> {
        const val NAME_KEY:String = "NameGame"
        const val ABOUT_KEY:String = "AboutGame"
        const val ID_KEY:String = "IdKey"
        override fun createFromParcel(parcel: Parcel): Game {
            return Game(parcel)
        }

        override fun newArray(size: Int): Array<Game?> {
            return arrayOfNulls(size)
        }
    }
}
