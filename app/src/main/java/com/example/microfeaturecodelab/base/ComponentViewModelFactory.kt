package com.example.microfeaturecodelab.base

import android.util.Log
import com.example.microfeaturecodelab.presentation.featureconfig.ComponentConfig
import com.example.microfeaturecodelab.presentation.uimodel.PersonalisedJobViewModel
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class ComponentViewModelFactory @Inject constructor(
    private val viewModelFactories: Map<String, @JvmSuppressWildcards MicroFeatureFactory<out MicroFeatureViewModel>>,
    private val composerFactories: Map<String, @JvmSuppressWildcards AbstractMicroFeatureComposer<out MicroFeatureViewModel>>,
    private val featureFactory: PersonalisedJobViewModel.Factory
) {

    fun create(
        componentConfig: ComponentConfig,
        viewModelScope: CoroutineScope
    ): MicroFeatureViewModel {
        return when (componentConfig.type) {
            "personalisedJob" -> {
                Log.d("ComponentViewModelFactory", "VM: ${viewModelFactories[componentConfig.type]}")
                Log.d("ComponentViewModelFactory", "Composer: ${composerFactories[componentConfig.type]}")
                val viewmodel = featureFactory.create(viewModelScope)
                viewmodel
            }
            "premiumApplicant" -> {
                Log.d("ComponentViewModelFactory", "Composer: ${composerFactories[componentConfig.type]}")
                val viewmodel = featureFactory.create(viewModelScope)
                viewmodel
            }
            else -> throw IllegalArgumentException("Unknown component type: ${componentConfig.type}")
        }
    }

}