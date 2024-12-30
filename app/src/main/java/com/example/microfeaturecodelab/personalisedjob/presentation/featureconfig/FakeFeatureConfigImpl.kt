package com.example.microfeaturecodelab.personalisedjob.presentation.featureconfig

import javax.inject.Inject

class FakeFeatureConfigImpl @Inject constructor(): FeatureConfig {

    /*
    * North Star
    * The component list can be retrieved from server which can contain following:
    * 1. type of component
    * 2. static data required to render the component
    * 3. input/endpoint to fetch the data (use endpoint mapper to map the input to the actual endpoint)
    * */
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