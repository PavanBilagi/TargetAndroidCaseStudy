package com.target.targetcasestudy.ui.dealdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.models.Deal
import com.target.targetcasestudy.data.DealsRepository
import com.target.targetcasestudy.ui.base.Status
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DealDetailsViewModel(
    dealId: String,
    private val repository: DealsRepository
) : ViewModel() {

    private val _dealStateFlow = MutableStateFlow<Status<Deal>>(Status.Loading())
    val dealStateFlow = _dealStateFlow.asStateFlow()


    init {
        fetchDeal(dealId)
    }

    private fun fetchDeal(dealId: String) {
        viewModelScope.launch(IO) {
            runCatching {
                repository.retrieveDeal(dealId)
            }.onFailure { error ->
                _dealStateFlow.update { Status.Error(throwable = error.cause) }
            }.onSuccess { response ->
                _dealStateFlow.update { Status.Success(response) }
            }
        }
    }

}

