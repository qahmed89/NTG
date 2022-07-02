package com.ahmed.ntg.presentation.screen.details

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Details(
    val fromBase: String,
    val fromValue: Float,
    val toBase: String,
    val toValue: Float
) : Parcelable

class DetailsType : NavType<Details>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Details? {
        return bundle.getParcelable(key)
    }
    override fun parseValue(value: String): Details {
        return Gson().fromJson(value, Details::class.java)
    }
    override fun put(bundle: Bundle, key: String, value: Details) {
        bundle.putParcelable(key, value)
    }
}