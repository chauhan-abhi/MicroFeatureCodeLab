package com.example.microfeaturecodelab.di

import com.example.microfeaturecodelab.personalisedjob.presentation.featureconfig.FakeFeatureConfigImpl
import com.example.microfeaturecodelab.personalisedjob.presentation.featureconfig.FeatureConfig
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class FeatureModule {

    @Binds
    internal abstract fun bindsFeatureConfig(impl: FakeFeatureConfigImpl): FeatureConfig
}