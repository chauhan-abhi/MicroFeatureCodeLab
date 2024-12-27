package com.example.microfeaturecodelab.premiumApplicant

import com.example.microfeaturecodelab.base.MicroFeatureFactory
import com.example.microfeaturecodelab.base.MicroFeatureViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope

class PremiumApplicantViewModel @AssistedInject constructor(
    @Assisted val coroutineScope: CoroutineScope
): MicroFeatureViewModel {

    @AssistedFactory
    interface Factory: MicroFeatureFactory<PremiumApplicantViewModel>
}