package com.poly.lab1234_ph51025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class ImageComponents : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppIcon()
                val images = listOf(
                    R.drawable.ic_launcher_background,
                    R.drawable.ic_launcher_foreground,
                    R.drawable.ic_launcher_foreground
                )
                HorizontalImageList(images)
                VerticalImageList(images)
            }
        }
    }


}
@Composable
fun AppIcon() {
    Icon(
        painter = painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = "Logo",
        modifier = Modifier
            .size(64.dp)
            .padding(16.dp)
    )
}

@Composable
fun HorizontalImageList(imageList: List<Int>) {
    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .horizontalScroll(scrollState)
            .padding(16.dp)
    ) {
        imageList.forEachIndexed { index, image ->
            ImageItem(image, Modifier.size(200.dp), index == 0)
        }
    }
}

@Composable
fun VerticalImageList(imageList: List<Int>) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        imageList.forEachIndexed { index, image ->
            ImageItem(image, Modifier.fillMaxWidth(), index == 0)
        }
    }
}

@Composable
fun ImageItem(image: Int, modifier: Modifier, isFirst: Boolean) {
    Image(
        painter = painterResource(id = image),
        contentDescription = "Image Description",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .padding(
                start = if (isFirst) 0.dp else 8.dp,
                end = 8.dp,
                top = if (isFirst) 0.dp else 8.dp,
                bottom = 8.dp
            )
    )
}
