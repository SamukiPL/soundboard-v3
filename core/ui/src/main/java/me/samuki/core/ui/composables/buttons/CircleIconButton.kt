package me.samuki.core.ui.composables.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
public fun CircleIconButton(
    onClick: () -> Unit,
    contentDescription: String,
    modifier: Modifier = Modifier,
    imageVector: ImageVector? = null,
    painter: Painter? = null,
    background: Color = MaterialTheme.colorScheme.primary,
    tint: Color = MaterialTheme.colorScheme.onPrimary,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .clip(CircleShape)
            .aspectRatio(1f)
            .background(background)
    ) {
        when {
            imageVector != null -> Icon(
                imageVector = imageVector,
                contentDescription = contentDescription,
                tint = tint,
            )
            painter != null -> Icon(
                painter = painter,
                contentDescription = contentDescription,
                tint = tint,
            )
            else -> error("imageVector and painter cannot be both null")
        }
    }
}

@Preview
@Composable
private fun CircleIconButtonPreview() {
    CircleIconButton(
        {},
        "",
        imageVector = Icons.Default.PlayCircle,
        modifier = Modifier
            .size(60.dp)
    )
}
