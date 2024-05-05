package com.cabify.domain.store

import com.cabify.commons.di.BackDispatchers
import com.cabify.domain.response.MyResult
import com.cabify.domain.response.UiApiError
import com.cabify.domain.response.map
import com.cabify.domain.response.mapError
import com.cabify.domain.response.toBasicUi
import com.cabify.domain.usecase.UseCaseSuspend
import kotlinx.coroutines.withContext

class GetStoreItemsUseCase(
    private val dispatchers: BackDispatchers,
    private val dataSource: StoreDataSource,
) : UseCaseSuspend<Unit, MyResult<List<ProductUi>, UiApiError>> {
    override suspend fun invoke(params: Unit): MyResult<List<ProductUi>, UiApiError> =
        withContext(dispatchers.io) {
            dataSource.getStoreItems()
                .map { it.products.map { product ->
                    ProductUi(
                        codeStringToEnum(product.code),
                        product.name,
                        product.price,
                    )
                } }
                .mapError { it.toBasicUi() }
        }

    private fun codeStringToEnum(code: String): Code = when (code) {
        "VOUCHER" -> Code.VOUCHER
        "TSHIRT" -> Code.T_SHIRT
        else -> Code.MUG
    }
}
