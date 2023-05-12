package com.villadevs.amphibians.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.villadevs.amphibians.model.Amphibian
import com.villadevs.amphibians.network.AmphibianApi
import kotlinx.coroutines.launch

class AmphibianViewModel : ViewModel() {

    enum class AmphibianApiStatus { LOADING, ERROR, DONE }

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<AmphibianApiStatus>()
    val status: LiveData<AmphibianApiStatus> = _status

    private val _amphibian = MutableLiveData<List<Amphibian>>()
    val amphibian: LiveData<List<Amphibian>> = _amphibian

    private var _currentAmphibian = MutableLiveData<Amphibian>()
    val currentAmphibian: LiveData<Amphibian> = _currentAmphibian

    init {
        getAmphibiansDatas()
    }

    private fun getAmphibiansDatas() {
        viewModelScope.launch {
            _status.value = AmphibianApiStatus.LOADING
            try {
                _amphibian.value = AmphibianApi.retrofitService.getAmphibians()
                _status.value = AmphibianApiStatus.DONE

            } catch (e: Exception) {
                _status.value = AmphibianApiStatus.ERROR
                _amphibian.value = listOf()
            }

        }

    }

    fun updateCurrentAmphibian(amphibian: Amphibian) {
        _currentAmphibian.value = amphibian
    }

}