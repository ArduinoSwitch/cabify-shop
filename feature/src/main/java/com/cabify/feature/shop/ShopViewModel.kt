package com.cabify.feature.shop

import androidx.annotation.StringRes
import com.cabify.commons.di.FrontDispatchers
import com.cabify.commons.navigation.Navigator
import com.cabify.commons.navigation.dialog.DialogData
import com.cabify.commons.ui.BaseViewModel
import com.cabify.domain.response.UiApiError
import com.cabify.domain.response.onFailure
import com.cabify.domain.response.onSuccess
import com.cabify.domain.store.Code
import com.cabify.domain.store.GetStoreItemsUseCase
import com.cabify.domain.store.ProductUi
import com.cabify.feature.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ShopViewModel(
    dispatcher: FrontDispatchers,
    private val navigator: Navigator,
    getStoreItemsUseCase: GetStoreItemsUseCase,
) : BaseViewModel(dispatcher) {

    val productList = MutableStateFlow(emptyList<ProductUi>())
    val productBasket = MutableStateFlow(emptyList<ProductUi>())
    val voucherCounter = productBasket.map { list ->
        list.filter { item -> item.code == Code.VOUCHER }.size
    }
    val shirtCounter = productBasket.map { list ->
        list.filter { item -> item.code == Code.T_SHIRT }.size
    }
    val mugCounter = productBasket.map { list ->
        list.filter { item -> item.code == Code.MUG }.size
    }
    val voucherButton = voucherCounter.map {
        it > 0
    }
    val shirtButton = shirtCounter.map {
        it > 0
    }
    val mugButton = mugCounter.map {
        it > 0
    }
    val itemCounter = productBasket.map {
        it.size
    }

    val productBaseketWithDescountAndOrdered = productBasket.map { list ->
        getDiscountForShirts(list.filter { it.code == Code.T_SHIRT }) +
        getDiscountForVouchers(list.filter { it.code == Code.VOUCHER }) +
        list.filter { it.code == Code.MUG }
    }

    val totalBasket = productBaseketWithDescountAndOrdered.map {list ->
        "Total: ${list.sumOf { item -> item.price }}€"
    }

    init {
        scope.launch {
            getStoreItemsUseCase.invoke(Unit)
                .onSuccess { list ->
                    productList.value = list
                }
                .onFailure { uiError ->
                    when (uiError) {
                        UiApiError.Generic -> openDialog(
                            R.string.dialog_generic_title,
                            R.string.dialog_generic_desc,
                        )
                        UiApiError.NoInternet -> openDialog(
                            R.string.dialog_no_connection_title,
                            R.string.dialog_no_connection_description,
                        )
                    }
                }
        }
    }

    private fun getDiscountForVouchers(items: List<ProductUi>): List<ProductUi> =
        items.mapIndexed { index, productUi ->
            if ((index+ 1)%2 == 0) productUi.copy(price = 0.0)
            else productUi
        }

    private fun getDiscountForShirts(items: List<ProductUi>): List<ProductUi> =
        if (items.size >= 3) items.map { item -> item.copy(price = 19.00) }
        else items

    private fun openDialog(
        @StringRes title: Int,
        @StringRes description: Int,
    ) {
        navigator.openDialog(
            DialogData.Informative(
                title = title,
                description = description,
            )
        )
    }

    fun addItem(item: ProductUi) {
        val tempList = productBasket.value
        productBasket.value = tempList + item
    }

    fun removeItem(item: ProductUi) {
        val tempList = productBasket.value
        if (tempList.contains(item)) {
            productBasket.value = tempList - item
        }
    }

    fun navigateBack() {
        navigator.back()
    }

    fun navigateToResumeBasket() {
        navigator.goTo(ShopFragmentDirections.actionShopToResult())
    }
}
