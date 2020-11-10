package com.dester.androidcourse2020github.model

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import java.io.Serializable

data class Song(@DrawableRes val poster: Int,val title:String,@RawRes val audio:Int,val author:String):Serializable {

}