package com.dester.androidcourse2020github

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable


data class Game(val name:String,  val about:String){



    override fun equals(other: Any?): Boolean {
        if(other is Game){
            return (name == other.name && about == other.about)
        }
        return false
    }

    companion object{
        const val NAME_KEY: String = "NameGame"
        const val ABOUT_KEY: String = "AboutGame"
        const val ID_KEY: String = "IdKey"
    }
}
