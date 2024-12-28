package com.example.microfeaturecodelab.personalisedjob.di

import com.example.microfeaturecodelab.personalisedjob.data.JobsRepositoryImpl
import com.example.microfeaturecodelab.personalisedjob.data.network.DataSource
import com.example.microfeaturecodelab.personalisedjob.data.network.LocalDataSourceImpl
import com.example.microfeaturecodelab.personalisedjob.domain.JobsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindsJobRepository(impl: JobsRepositoryImpl): JobsRepository

    @Binds
    internal abstract fun bindsNetworkDataSource(impl: LocalDataSourceImpl): DataSource
}