package com.example.microfeaturecodelab.recentsearches.di

import com.example.microfeaturecodelab.base.AbstractMicroFeatureComposer
import com.example.microfeaturecodelab.base.MicroFeatureFactory
import com.example.microfeaturecodelab.base.MicroFeatureViewModel
import com.example.microfeaturecodelab.di.ComponentTypeKey
import com.example.microfeaturecodelab.premiumApplicant.presentation.PremiumApplicantComposer
import com.example.microfeaturecodelab.premiumApplicant.presentation.PremiumApplicantViewModel
import com.example.microfeaturecodelab.recentsearches.presentation.RecentSearchesListComposer
import com.example.microfeaturecodelab.recentsearches.presentation.RecentSearchesViewModel
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
    @ComponentTypeKey("recentSearches")
    fun bindRecentSearchesComposer(composer: RecentSearchesListComposer): AbstractMicroFeatureComposer<out MicroFeatureViewModel>

    @Binds
    @IntoMap
    @ComponentTypeKey("recentSearches")
    fun bindRecentSearchesViewModel(viewmodelFactory: RecentSearchesViewModel.Factory): MicroFeatureFactory<out MicroFeatureViewModel>

}