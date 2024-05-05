package com.cabify.feature.result

import android.os.Bundle
import android.view.View
import com.cabify.commons.binding.list.GenericListAdapter
import com.cabify.commons.binding.onClick
import com.cabify.commons.binding.viewBinding
import com.cabify.commons.ui.BaseFragment
import com.cabify.domain.store.Code
import com.cabify.domain.store.ProductUi
import com.cabify.feature.R
import com.cabify.feature.databinding.ResultFragmentBinding
import com.cabify.feature.databinding.ResumeBasketItemBinding
import com.cabify.feature.shop.ShopViewModel
import org.koin.androidx.navigation.koinNavGraphViewModel

class ResultFragment: BaseFragment(R.layout.result_fragment) {

    override val viewModel: ShopViewModel by koinNavGraphViewModel(R.id.shop_graph)
    private val binding by viewBinding(ResultFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bind()
    }

    private fun ResultFragmentBinding.bind() {
        header.backButton.onClick { viewModel.navigateBack() }
        basketItemsResume.bind(
            viewModel.productBaseketWithDescountAndOrdered,
            getAdapter()
        )
        totalBasket.bind(viewModel.totalBasket)
    }

    private fun getAdapter() = GenericListAdapter(
        getViewBinding = {parent, _ -> parent.viewBinding(ResumeBasketItemBinding::inflate) },
        areItemsSame = {first, second -> first == second},
        onBind = ::onProductBind
    )

    private fun onProductBind(
        item: ProductUi,
        binding: ResumeBasketItemBinding,
        position: Int
    ) {
        binding.icon.setImageResource(getIconFromCode(item.code))
        binding.name.text = item.name
        binding.price.text = item.price.toString()
    }

    private fun getIconFromCode(code: Code) = when (code) {
        Code.VOUCHER -> R.drawable.ic_voucher
        Code.T_SHIRT -> R.drawable.ic_shirt
        Code.MUG -> R.drawable.ic_mug
    }
}
