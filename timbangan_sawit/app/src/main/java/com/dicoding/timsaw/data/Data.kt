package com.dicoding.timsaw.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class Data(
    val results: ArrayList<Results>
) {
    @Parcelize
    data class Results(
        val id: Int,
        val no_weighing: Int,
        val date: String,
        val time: String,
        val driver_name: String,
        val no_vehicle: String,
        val many_bunches: Int,
        val bruto: Int,
        val tara: Int,
        val neto: Int
    ) : Parcelable
}
