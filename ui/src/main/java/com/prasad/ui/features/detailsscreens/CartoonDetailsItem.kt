package com.prasad.ui.features.detailsscreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.prasad.ui.R
import com.prasad.ui.features.listscreen.CartoonImage
import com.prasad.ui.models.Cartoon

@Composable
fun CartoonDetailsItem(
    modifier: Modifier = Modifier,
    cartoon: Cartoon
) {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        Card(
            modifier = modifier,
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            with(cartoon) {
                Column(modifier = modifier) {
                    CartoonImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp), imageUrl = image
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = stringResource(id = R.string.title, title),
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = stringResource(id = R.string.year, year))
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = stringResource(id = R.string.creator, creator.joinToString(" ")))
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = stringResource(id = R.string.genre, genre.joinToString(" ")))
            }

        }
    }
}