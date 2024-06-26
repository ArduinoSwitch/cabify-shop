package com.cabify.commons.binding.binders

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cabify.commons.binding.list.GenericListAdapter
import kotlinx.coroutines.flow.Flow

interface ListBinders : BaseBinder {
    fun <T> RecyclerView.bind(
        items: Flow<List<T>>,
        adapter: GenericListAdapter<T, *>,
        isVertical: Boolean = true,
        isReverse: Boolean = false,
    ) {
        setup(adapter, isVertical, isReverse)
        observe(items) {
            adapter.submitList(it)
        }
    }

    fun <T> RecyclerView.bindGrid(
        items: Flow<List<T>>,
        adapter: GenericListAdapter<T, *>,
        numberOfColumns: Int,
    ) {
        setupItem(adapter, numberOfColumns)
        observe(items) {
            adapter.submitList(it)
        }
    }
}

private fun RecyclerView.setupItem(
    adapter: GenericListAdapter<*, *>,
    numberOfRows: Int,
) {
    this.adapter = adapter
    this.layoutManager = GridLayoutManager(context, numberOfRows)
}

private fun RecyclerView.setup(
    adapter: GenericListAdapter<*, *>,
    isVertical: Boolean,
    isReverse: Boolean,
) {
    val orientation = if (isVertical) RecyclerView.VERTICAL else RecyclerView.HORIZONTAL
    this.adapter = adapter
    this.layoutManager = LinearLayoutManager(context, orientation, isReverse)
}
