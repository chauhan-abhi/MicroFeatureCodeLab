package com.example.microfeaturecodelab.presentation.featureconfig

import javax.inject.Inject

class FakeFeatureConfigImpl @Inject constructor(): FeatureConfig {
    override suspend fun getComponentsConfig(): List<ComponentConfig> {
        val type1List =  (1..20).map {
            ComponentConfig(it.toString(), "personalisedJob")
        }
        val type2List = (25..30).map {
            ComponentConfig(it.toString(), "premiumApplicant")
        }
        return type1List + type2List
    }
}