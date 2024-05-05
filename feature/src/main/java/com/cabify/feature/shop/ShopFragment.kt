package com.cabify.feature.shop

import android.os.Bundle
import android.view.View
import com.cabify.commons.binding.list.GenericListAdapter
import com.cabify.commons.binding.onClick
import com.cabify.commons.binding.viewBinding
import com.cabify.commons.ui.BaseFragment
import com.cabify.commons.ui.utils.observe
import com.cabify.domain.store.Code
import com.cabify.domain.store.ProductUi
import com.cabify.feature.R
import com.cabify.feature.databinding.ShopFragmentBinding
import com.cabify.feature.databinding.ShopItemBinding
import org.koin.androidx.navigation.koinNavGraphViewModel

class ShopFragment : BaseFragment(R.layout.shop_fragment) {
    private val binding by viewBinding(ShopFragmentBinding::bind)
    override val viewModel: ShopViewModel by koinNavGraphViewModel(R.id.shop_graph)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bind()
    }

    private fun ShopFragmentBinding.bind() {
        shopList.bindGrid(
            viewModel.productList,
            getAdapter(),
            2,
        )
        observe(viewModel.itemCounter) {
            shoppingBag.counter.text = it.toString()
        }
        shoppingBag.root.onClick {
            viewModel.navigateToResumeBasket()
        }
    }

    private fun getAdapter() = GenericListAdapter(
        getViewBinding = {parent, _ -> parent.viewBinding(ShopItemBinding::inflate)},
        areItemsSame = {first, second -> first == second},
        onBind = ::bindAdapterItem
    )

    private fun bindAdapterItem(
        item: ProductUi,
        binding: ShopItemBinding,
        position: Int,
    ) {
        binding.title.text = item.name
        when(item.code) {
            Code.VOUCHER -> {
                binding.itemImage.setImageResource(R.drawable.ic_voucher)
                binding.removeItem.bindEnabled(viewModel.voucherButton)
                binding.itemCount.bind(viewModel.voucherCounter)
            }
            Code.T_SHIRT -> {
                binding.itemImage.setImageResource(R.drawable.ic_shirt)
                binding.removeItem.bindEnabled(viewModel.shirtButton)
                binding.itemCount.bind(viewModel.shirtCounter)
            }
            Code.MUG -> {
                binding.itemImage.setImageResource(R.drawable.ic_mug)
                binding.removeItem.bindEnabled(viewModel.mugButton)
                binding.itemCount.bind(viewModel.mugCounter)
            }
        }
        binding.addItem.onClick { viewModel.addItem(item) }
        binding.removeItem.onClick { viewModel.removeItem(item) }
    }
}
