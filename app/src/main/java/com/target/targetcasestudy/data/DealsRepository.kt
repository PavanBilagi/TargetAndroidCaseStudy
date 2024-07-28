package com.target.targetcasestudy.data

import com.target.targetcasestudy.models.Deal
import com.target.targetcasestudy.models.DealResponse


interface DealsRepository {
    suspend fun fetchAllDeals(): DealResponse
    suspend fun retrieveDeal(dealId: String): Deal
}
