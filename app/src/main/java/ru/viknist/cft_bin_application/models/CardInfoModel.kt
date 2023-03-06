package ru.viknist.cft_bin_application.models

import com.google.gson.annotations.SerializedName

data class CardInfoModel (
    @SerializedName("number") val number: NumberModel?,
    @SerializedName("scheme") val scheme: String?,
    @SerializedName("type") val type: String?,
    @SerializedName("brand") val brand: String?,
    @SerializedName("prepaid") val prepaid: Boolean?,
    @SerializedName("country") val country: CountryModel?,
    @SerializedName("bank") val bank: BankModel?
): java.io.Serializable