package me.samuki.rationale.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import me.samuki.feature.rationale.R
import me.samuki.rationale.logic.RationaleContract

@Composable
internal fun RationaleContent(
    @Suppress("UNUSED_PARAMETER") state: RationaleContract.State,
    @Suppress("UNUSED_PARAMETER") onEvent: (RationaleContract.Event) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 14.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                "Potrzebne dodatkowe uprawnienia",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(top = 30.dp),
            )
            Image(
                painter = painterResource(id = R.drawable.image_rationale_mobile_with_padlock),
                contentDescription = "Rationale image",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 40.dp)
                    .size(320.dp)
            )
            Text(
                buildAnnotatedString {
                    append("W celu ustawienia dźwięku jako powiadomienie potrzebujemy udzielenia zgody na dostęp do pamięci telefonu. ")
                    withStyle(
                        SpanStyle(
                            fontStyle = MaterialTheme.typography.bodyMedium.fontStyle,
                            fontWeight = FontWeight.ExtraBold,
                        )
                    ) {
                        append("Nie ingerujemy w żadne Twoje pliki. ")
                    }
                    append("Dostęp jest potrzebny w celu zapisania dźwięku w pamięci Twojego telefonu")
                },
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(top = 40.dp, start = 10.dp, end = 10.dp)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 60.dp)
        ) {
            OutlinedButton(
                onClick = { onEvent(RationaleContract.Event.GoBack) },
                modifier = Modifier.width(180.dp)
            ) {
                Text("Anuluj", color = Color.Red)
            }
            Button(
                onClick = { onEvent(RationaleContract.Event.GoBack) },
                modifier = Modifier.width(180.dp)
            ) {
                Text("Przejdź do ustawień")
            }
        }
    }
}
