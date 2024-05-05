package com.cabify.domain.store

import kotlinx.serialization.Serializable

@Serializable
data class StoreDataModel(
    val products: List<Product>,
)

@Serializable
data class Product(
    val code: String,
    val name: String,
    val price: Double,
)
