package me.samuki.core.ui.previews

import android.net.Uri
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import me.samuki.model.Combinable
import me.samuki.model.Pause
import me.samuki.model.Sound
import me.samuki.model.values.Id
import me.samuki.model.values.LikeState
import me.samuki.model.values.Name
import me.samuki.model.values.Path
import me.samuki.model.values.Supplement

public class PreviewCombinableProvider : PreviewParameterProvider<Combinable> {

    override val values: Sequence<Combinable>
        get() = sequenceOf(
            Sound(
                id = Id(0),
                supplement = Supplement(""),
                name = Name("Preview Sound Name"),
                path = Path(Uri.EMPTY),
                likeState = LikeState.Normal
            ),
            Pause(
                repeats = 3
            )
        )
}
