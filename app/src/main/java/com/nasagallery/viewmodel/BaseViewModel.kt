package com.nasagallery.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

abstract class BaseViewModel : ViewModel() {
    // A CoroutineScope tied to the ViewModel's lifecycle
    private val job = Job()
    protected val viewModelScope = CoroutineScope(Dispatchers.Main + job)

    override fun onCleared() {
        super.onCleared()
        // Cancel the ViewModel's scope when the ViewModel is cleared
        viewModelScope.cancel()
    }
}
