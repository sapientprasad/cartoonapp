package com.prasad.ui.features.listscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.prasad.ui.models.Cartoon

@Composable
fun CartoonListItem(
    modifier: Modifier,
    cartoon: Cartoon,
    onItemClick: (id: Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(100.dp)
            .padding(5.dp)
            .wrapContentHeight()
            .clickable {
                onItemClick(cartoon.id)
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {

        Row(modifier = Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {

            CartoonImage(modifier = modifier.weight(0.3f), imageUrl = cartoon.image)
            CartoonRowItem(
                modifier = modifier
                    .weight(0.7f)
                    .fillMaxHeight()
                    .padding(start = 10.dp),
                cartoon = cartoon
            )

        }
    }
}