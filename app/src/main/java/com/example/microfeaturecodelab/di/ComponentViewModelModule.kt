package com.example.microfeaturecodelab.di

import com.example.microfeaturecodelab.base.MicroFeatureFactory
import com.example.microfeaturecodelab.base.MicroFeatureViewModel
import com.example.microfeaturecodelab.premiumApplicant.PremiumApplicantViewModel
import com.example.microfeaturecodelab.personalisedjob.presentation.uimodel.PersonalisedJobViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ViewModelComponent::class)
interface ComponentViewModelModule {

    @Binds
    @IntoMap
    @ComponentTypeKey("personalisedJob")
    fun bindPersonalisedViewModel(viewmodelFactory: PersonalisedJobViewModel.Factory): MicroFeatureFactory<out MicroFeatureViewModel>

    @Binds
    @IntoMap
    @ComponentTypeKey("premiumApplicant")
    fun bindPremiumApplicantViewModel(viewmodelFactory: PremiumApplicantViewModel.Factory): MicroFeatureFactory<out MicroFeatureViewModel>

}