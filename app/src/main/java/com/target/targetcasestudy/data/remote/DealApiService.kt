package com.target.targetcasestudy.data.remote

import com.target.targetcasestudy.models.Deal
import com.target.targetcasestudy.models.DealResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DealApiService {

    @GET("${BASE_URL}deals")
    suspend fun retrieveDeals(): DealResponse

    @GET("${BASE_URL}deals/{dealId}")
    suspend fun retrieveDeal(@Path("dealId") dealId: String): Deal
}
