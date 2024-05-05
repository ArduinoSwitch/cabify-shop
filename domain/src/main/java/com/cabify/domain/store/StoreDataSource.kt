package com.cabify.domain.store

import com.cabify.domain.response.GenericApiError
import com.cabify.domain.response.MyResult

interface StoreDataSource {
    suspend fun getStoreItems(): MyResult<StoreDataModel, GenericApiError>
}
