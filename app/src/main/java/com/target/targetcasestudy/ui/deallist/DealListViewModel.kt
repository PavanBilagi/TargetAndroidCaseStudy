package com.target.targetcasestudy.ui.deallist

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

class DealListViewModel(
    private val repository: DealsRepository
) : ViewModel() {

    private val _dealListStateFlow = MutableStateFlow<Status<List<Deal>>>(Status.Loading())
    val dealListStateFlow = _dealListStateFlow.asStateFlow()

    init {
        fetchDeals()
    }

    private fun fetchDeals() {
        viewModelScope.launch(IO) {
            runCatching {
                repository.fetchAllDeals()
            }.onFailure { error ->
                _dealListStateFlow.update { Status.Error(throwable = error.cause) }
            }.onSuccess { response ->
                _dealListStateFlow.update { Status.Success(response.deals) }
            }
        }
    }

}