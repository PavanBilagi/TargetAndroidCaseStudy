package com.target.targetcasestudy.data.remote

import com.target.targetcasestudy.models.Deal
import com.target.targetcasestudy.data.DealsRepository
import com.target.targetcasestudy.models.DealResponse

class DealsRemoteRepository(private val dealApiKtx: DealApiService) : DealsRepository {

    override suspend fun fetchAllDeals(): DealResponse {
        return dealApiKtx.retrieveDeals()
    }

    override suspend fun retrieveDeal(dealId: String): Deal {
        return dealApiKtx.retrieveDeal(dealId)
    }
}