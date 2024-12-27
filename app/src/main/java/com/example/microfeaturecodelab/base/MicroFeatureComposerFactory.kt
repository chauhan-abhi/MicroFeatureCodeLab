package com.example.microfeaturecodelab.base

import javax.inject.Inject
import kotlin.reflect.KClass

class MicroFeatureComposerFactory @Inject constructor() {

    private val creators =
        mutableMapOf<KClass<out MicroFeatureViewModel>, () -> AbstractMicroFeatureComposer<out MicroFeatureViewModel>>()

    fun <V : MicroFeatureViewModel> register(
        viewModelClass: KClass<V>,
        creator: () -> AbstractMicroFeatureComposer<V>
    ) {
        creators[viewModelClass] = creator
    }

    // Create a composer instance for the given ViewModel class
    fun <V : MicroFeatureViewModel> create(viewModelClass: KClass<V>): AbstractMicroFeatureComposer<V>? {
        @Suppress("UNCHECKED_CAST")
        return creators[viewModelClass]?.invoke() as? AbstractMicroFeatureComposer<V>
    }
}