package com.example.microfeaturecodelab.base

import com.example.microfeaturecodelab.presentation.featureconfig.ComponentConfig
import com.example.microfeaturecodelab.presentation.uimodel.PersonalisedJobViewModel
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class ComponentViewModelFactory @Inject constructor(
    private val featureFactory: PersonalisedJobViewModel.Factory
) {

    fun create(
        componentConfig: ComponentConfig,
        viewModelScope: CoroutineScope
    ): MicroFeatureViewModel {
        return when (componentConfig.type) {
            "personalisedJob" -> featureFactory.create(viewModelScope)
            else -> throw IllegalArgumentException("Unknown component type: ${componentConfig.type}")
        }
    }

}