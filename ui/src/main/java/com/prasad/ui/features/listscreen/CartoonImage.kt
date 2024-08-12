package com.prasad.ui.features.listscreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.prasad.ui.R

@Composable
fun CartoonImage(
    modifier: Modifier,
    imageUrl: String
) {
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .error(R.drawable.ic_default_image_bg).build(),
        contentDescription = stringResource(id = R.string.image_content_desc),
        contentScale = ContentScale.Crop
    )
}