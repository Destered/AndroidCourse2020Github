package com.dester.androidcourse2020github

import android.util.Log

open class Creature(val name:String,val type:String) {

    open fun test(){
        println("You create a Creature: $name")
    }
}