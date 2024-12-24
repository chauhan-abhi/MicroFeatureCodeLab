package com.example.microfeaturecodelab.presentation.featureconfig

interface FeatureConfig {

    suspend fun getComponentsConfig(): List<ComponentConfig>

}