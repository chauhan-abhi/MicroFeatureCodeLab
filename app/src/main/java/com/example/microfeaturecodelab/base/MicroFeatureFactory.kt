package com.example.microfeaturecodelab.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted

interface MicroFeatureFactory<T> {
    fun create(
        coroutineScope: CoroutineScope,
        fetchStrategy: SharingStarted
    ): T
}