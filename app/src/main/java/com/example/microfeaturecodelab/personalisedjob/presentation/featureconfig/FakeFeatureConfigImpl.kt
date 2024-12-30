package com.example.microfeaturecodelab.personalisedjob.presentation.featureconfig

import android.util.Log
import com.example.microfeaturecodelab.di.AppDispatchers
import com.example.microfeaturecodelab.di.Dispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FakeFeatureConfigImpl @Inject constructor(
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
): FeatureConfig {

    /*
    * North Star
    * The component list can be retrieved from server which can contain following:
    * 1. type of component
    * 2. static data required to render the component
    * 3. input/endpoint to fetch the data (use endpoint mapper to map the input to the actual endpoint)
    * */
    override suspend fun getComponentsConfig(): List<ComponentConfig> {
        return withContext(ioDispatcher) {
            Log.d("MicroFeature", "Fetching widgets ")
            delay(2000)
            val type1List =  (1..20).map {
                ComponentConfig(it.toString(), "personalisedJob")
            }
            val type2List = (25..30).map {
                ComponentConfig(it.toString(), "premiumApplicant")
            }
            Log.d("MicroFeature", "Fetched widgets ")
            type1List + type2List
        }
    }
}