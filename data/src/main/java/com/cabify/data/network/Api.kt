package com.cabify.data.network

import com.cabify.domain.store.StoreDataModel
import retrofit2.http.GET

interface Api {
    @GET("6c19259bd32dd6aafa327fa557859c2f/raw/ba51779474a150ee4367cda4f4ffacdcca479887/Products.json")
    suspend fun getStoreItems(): StoreDataModel
}
