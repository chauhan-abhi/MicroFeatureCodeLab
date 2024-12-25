package com.example.microfeaturecodelab.presentation.featureconfig

import androidx.compose.runtime.Stable

@Stable
data class ComponentConfig(
    val id: String,
    val type: String
)