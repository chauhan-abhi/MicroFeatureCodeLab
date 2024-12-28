package com.example.microfeaturecodelab.premiumApplicant.di

import com.example.microfeaturecodelab.base.AbstractMicroFeatureComposer
import com.example.microfeaturecodelab.base.MicroFeatureFactory
import com.example.microfeaturecodelab.base.MicroFeatureViewModel
import com.example.microfeaturecodelab.di.ComponentTypeKey
import com.example.microfeaturecodelab.premiumApplicant.presentation.PremiumApplicantComposer
import com.example.microfeaturecodelab.premiumApplicant.presentation.PremiumApplicantViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ViewModelComponent::class)
interface ComponentComposerModule {

    @Binds
    @IntoMap
    @ComponentTypeKey("premiumApplicant")
    fun bindPremiumApplicantComposer(composer: PremiumApplicantComposer): AbstractMicroFeatureComposer<out MicroFeatureViewModel>

    @Binds
    @IntoMap
    @ComponentTypeKey("premiumApplicant")
    fun bindPremiumApplicantViewModel(viewmodelFactory: PremiumApplicantViewModel.Factory): MicroFeatureFactory<out MicroFeatureViewModel>

}