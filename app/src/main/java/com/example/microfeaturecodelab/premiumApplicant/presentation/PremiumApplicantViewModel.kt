package com.example.microfeaturecodelab.premiumApplicant.presentation

import com.example.microfeaturecodelab.base.MicroFeatureFactory
import com.example.microfeaturecodelab.base.MicroFeatureViewModel
import com.example.microfeaturecodelab.personalisedjob.presentation.featureconfig.FetchStrategy
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted

class PremiumApplicantViewModel @AssistedInject constructor(
    @Assisted val coroutineScope: CoroutineScope,
    @Assisted val sharingStarted: SharingStarted
): MicroFeatureViewModel {

    @AssistedFactory
    interface Factory: MicroFeatureFactory<PremiumApplicantViewModel>
}