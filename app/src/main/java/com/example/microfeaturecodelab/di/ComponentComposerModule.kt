package com.example.microfeaturecodelab.di

import com.example.microfeaturecodelab.base.AbstractMicroFeatureComposer
import com.example.microfeaturecodelab.base.MicroFeatureViewModel
import com.example.microfeaturecodelab.premiumApplicant.PremiumApplicantComposer
import com.example.microfeaturecodelab.presentation.composables.JobRecommendationComposer
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
    @ComponentTypeKey("personalisedJob")
    fun bindPersonalisedComposer(composer: JobRecommendationComposer): AbstractMicroFeatureComposer<out MicroFeatureViewModel>

    @Binds
    @IntoMap
    @ComponentTypeKey("premiumApplicant")
    fun bindPremiumApplicantComposer(composer: PremiumApplicantComposer): AbstractMicroFeatureComposer<out MicroFeatureViewModel>
}