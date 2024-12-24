package com.example.microfeaturecodelab.base

import kotlinx.coroutines.CoroutineScope

interface MicroFeatureFactory<T> {
    fun create(coroutineScope: CoroutineScope): T
}