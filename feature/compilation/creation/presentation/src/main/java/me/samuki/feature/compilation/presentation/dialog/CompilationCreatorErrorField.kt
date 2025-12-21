package me.samuki.feature.compilation.presentation.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun CompilationCreatorErrorField(
    showErrorOnDialog: ShowErrorOnDialog,
    modifier: Modifier = Modifier,
) = if (showErrorOnDialog) {
    Text(
        "Something went wrong, please try again later",
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.errorContainer)
            .padding(8.dp),
        color = MaterialTheme.colorScheme.onError,
        fontSize = 14.sp,
        textAlign = TextAlign.Center
    )
} else Unit

@Preview
@Composable
private fun CompilationCreatorErrorFieldPreview() = Column {
    CompilationCreatorErrorField(true)
    CompilationCreatorErrorField(false)
}
