package com.hicoding.smkpubmak.views.utils

import android.location.Location


fun getDistance(lat: Double, long: Double) : Double{
    val myLocation = Location("A")
    myLocation.latitude = lat
    myLocation.longitude = long

    //SMK PUBLIK MAKASAR
    val targetLocation = Location("B")
    targetLocation.latitude = -5.140036
    targetLocation.longitude = 119.429705

    //meter
    return myLocation.distanceTo(targetLocation).toDouble()
}