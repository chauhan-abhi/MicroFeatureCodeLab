package com.example.microfeaturecodelab.personalisedjob.di

import com.example.microfeaturecodelab.base.AbstractMicroFeatureComposer
import com.example.microfeaturecodelab.base.MicroFeatureFactory
import com.example.microfeaturecodelab.base.MicroFeatureViewModel
import com.example.microfeaturecodelab.di.ComponentTypeKey
import com.example.microfeaturecodelab.personalisedjob.presentation.composables.JobRecommendationComposer
import com.example.microfeaturecodelab.personalisedjob.presentation.uimodel.PersonalisedJobViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ViewModelComponent::class)
interface PersonalisedComponentModule {

    @Binds
    @IntoMap
    @ComponentTypeKey("personalisedJob")
    fun bindPersonalisedComposer(composer: JobRecommendationComposer): AbstractMicroFeatureComposer<out MicroFeatureViewModel>

    @Binds
    @IntoMap
    @ComponentTypeKey("personalisedJob")
    fun bindPersonalisedViewModel(viewmodelFactory: PersonalisedJobViewModel.Factory): MicroFeatureFactory<out MicroFeatureViewModel>

}