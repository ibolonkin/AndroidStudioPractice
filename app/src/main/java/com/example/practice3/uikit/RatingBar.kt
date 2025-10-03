package com.example.practice3.uikit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RatingBar(
    rating: Float,
    modifier: Modifier = Modifier,
    maxRating: Int = 5,
    onRatingChanged: (Float) -> Unit
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        for (i in 1..maxRating) {
            val filled = i <= rating
            Icon(
                imageVector = if (filled) Icons.Filled.Star else Icons.Outlined.Star,
                contentDescription = "Star $i",
                tint = if (filled) Color.Yellow else Color.Gray,
                modifier = Modifier
                    .clickable { onRatingChanged(i.toFloat()) }
                    .size(36.dp)

            )
        }
    }
}