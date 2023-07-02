package me.samuki.feature.list

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ListScreen(
    navigation: ListNavigation
) {
    val viewModel: ListViewModel = hiltViewModel()

    Text(text = "List", style = TextStyle(
        fontSize = 34.sp
    ))
}
