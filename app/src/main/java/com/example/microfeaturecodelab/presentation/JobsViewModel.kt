package com.example.microfeaturecodelab.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.microfeaturecodelab.presentation.uimodel.PersonalisedJobViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JobsViewModel @Inject constructor(
    featureFactory: PersonalisedJobViewModel.Factory
) : ViewModel() {

    val feature = featureFactory.create(viewModelScope)

    init {
        Log.d("JobsViewModel", "JobsViewModel created")
    }
}