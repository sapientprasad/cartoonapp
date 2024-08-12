package com.prasad.ui.features.listscreen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.prasad.ui.models.CartoonList


@Composable
fun CartoonList(
    modifier: Modifier = Modifier,
    cartoonList: CartoonList,
    onItemClick: (Int) -> Unit
) {
    LazyColumn {
        items(cartoonList.cartoonList) {
            CartoonListItem(modifier = modifier, cartoon = it, onItemClick)
        }
    }
}