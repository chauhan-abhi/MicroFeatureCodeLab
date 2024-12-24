package com.example.microfeaturecodelab.presentation.featureconfig

import javax.inject.Inject

class FakeFeatureConfigImpl @Inject constructor(): FeatureConfig {
    override suspend fun getComponentsConfig(): List<ComponentConfig> {
        return (1..20).map {
            ComponentConfig(it.toString(), "personalisedJob")
        }
        return listOf(
            ComponentConfig("1", "personalisedJob"),
            ComponentConfig("2", "personalisedJob"),
            ComponentConfig("3", "personalisedJob"),
            ComponentConfig("4", "personalisedJob"),
            ComponentConfig("5", "personalisedJob"),
            ComponentConfig("6", "personalisedJob"),
            ComponentConfig("7", "personalisedJob"),
            ComponentConfig("8", "personalisedJob"),
            ComponentConfig("9", "personalisedJob"),
            ComponentConfig("10", "personalisedJob"),
        )
    }
}