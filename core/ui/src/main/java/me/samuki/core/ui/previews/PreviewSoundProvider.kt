package me.samuki.core.ui.previews

import android.net.Uri
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import me.samuki.model.Sound
import me.samuki.model.util.EMPTY_STRING
import me.samuki.model.values.Id
import me.samuki.model.values.LikeState
import me.samuki.model.values.Name
import me.samuki.model.values.Path
import me.samuki.model.values.Supplement

public class PreviewSoundProvider : PreviewParameterProvider<Sound> {

    override val values: Sequence<Sound>
        get() = List(10) {
            Sound(
                id = Id(it),
                supplement = Supplement(EMPTY_STRING),
                name = Name("Preview Sound Name"),
                likeState = LikeState.Normal,
                path = Path(Uri.EMPTY)
            )
        }.asSequence()
}
