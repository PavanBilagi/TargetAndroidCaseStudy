package com.target.targetcasestudy.di

import com.target.targetcasestudy.data.remote.DealsRemoteRepository
import com.target.targetcasestudy.data.DealsRepository
import com.target.targetcasestudy.data.remote.provideConverterFactory
import com.target.targetcasestudy.data.remote.provideHttpClient
import com.target.targetcasestudy.data.remote.provideRetrofit
import com.target.targetcasestudy.data.remote.provideService
import com.target.targetcasestudy.ui.dealdetails.DealDetailsViewModel
import com.target.targetcasestudy.ui.deallist.DealListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single { provideHttpClient() }
    single { provideConverterFactory() }
    single { provideRetrofit(get(), get()) }
    single { provideService(get()) }
    single<DealsRepository> { DealsRemoteRepository(get()) }
    viewModelOf(::DealListViewModel)
    viewModelOf(::DealDetailsViewModel)
}
