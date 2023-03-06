package ru.viknist.cft_bin_application.models

import com.google.gson.annotations.SerializedName

data class BankModel(
    @SerializedName("name") val name: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("phone") val phone: String?,
    @SerializedName("city") val city: String?
): java.io.Serializable
