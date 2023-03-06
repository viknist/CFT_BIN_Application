package ru.viknist.cft_bin_application.network

import retrofit2.http.GET
import retrofit2.http.Path
import ru.viknist.cft_bin_application.models.CardInfoModel

interface Api {
    @GET("{id}")
    suspend fun getCardInfo(
        @Path("id") id: String
    ): CardInfoModel
}