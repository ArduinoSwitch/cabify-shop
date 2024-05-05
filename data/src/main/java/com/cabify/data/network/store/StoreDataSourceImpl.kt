package com.cabify.data.network.store

import com.cabify.data.network.Api
import com.cabify.domain.response.ApiErrorHandling
import com.cabify.domain.response.GenericApiError
import com.cabify.domain.response.MyResult
import com.cabify.domain.store.StoreDataModel
import com.cabify.domain.store.StoreDataSource

class StoreDataSourceImpl(
    private val api: Api
) : StoreDataSource {
    override suspend fun getStoreItems(): MyResult<StoreDataModel, GenericApiError> =
        ApiErrorHandling.run {
            api.getStoreItems()
        }
}
