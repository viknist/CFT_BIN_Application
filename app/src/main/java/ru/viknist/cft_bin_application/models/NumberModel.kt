package ru.viknist.cft_bin_application.models

import com.google.gson.annotations.SerializedName

data class NumberModel(
    @SerializedName("length") val length: Int?,
    @SerializedName("luhn") val luhn: Boolean?
): java.io.Serializable
