package com.example.microfeaturecodelab.di

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val dispatchers: AppDispatchers)

enum class AppDispatchers {
    Default,
    IO,
}
