package com.verygoodconsulting.aiprompts.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.verygoodconsulting.aiprompts.domain.HomeScreenData
import com.verygoodconsulting.aiprompts.domain.HomeUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mHomeUsecase: HomeUsecase
) : ViewModel() {

    private val mExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    init {
        viewModelScope.launch(Dispatchers.IO + mExceptionHandler) {
            mHomeUsecase.loadInfo()
        }
    }

    private val _state = run {
        mHomeUsecase.data()
    }.stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = HomeScreenData.Dummy("Dummy value")
    )

    fun data(): StateFlow<HomeScreenData> {
        return _state
    }
}