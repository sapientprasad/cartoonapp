package com.prasad.ui.features.listscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.prasad.ui.R
import com.prasad.ui.models.Cartoon

@Composable
fun CartoonRowItem(
    modifier: Modifier,
    cartoon: Cartoon
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.SpaceAround) {
        with(cartoon)
        {
            Text(text = title, style = MaterialTheme.typography.titleLarge)
            Text(
                text = stringResource(id = R.string.creator, creator.joinToString(" ")),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = stringResource(id = R.string.genre, genre.joinToString(" ")),
                style = MaterialTheme.typography.bodySmall
            )

        }
    }

}