package com.example.microfeaturecodelab.personalisedjob.presentation.featureconfig

interface FeatureConfig {

    suspend fun getComponentsConfig(): List<ComponentConfig>

}