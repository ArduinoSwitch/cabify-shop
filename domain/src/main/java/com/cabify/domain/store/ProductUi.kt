package com.cabify.domain.store

data class ProductUi(
    val code: Code,
    val name: String,
    val price: Double,
)

enum class Code {
    VOUCHER, T_SHIRT, MUG
}
