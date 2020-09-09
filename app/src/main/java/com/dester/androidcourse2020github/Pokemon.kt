package com.dester.androidcourse2020github

import android.util.Log

class Pokemon (name:String, type:String, val power: Int) :Creature(name,type),Comparable<Pokemon> {
    override fun test(){
        println("You create a Pokemon: $name")
    }

    override fun compareTo(other: Pokemon): Int {
        return power.compareTo(other.power)
    }
}