package me.samuki.resource.sounds.reader

import android.content.Context
import android.media.MediaMetadataRetriever
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import me.samuki.model.values.Name
import javax.inject.Inject

internal class GetSoundName @Inject constructor(
    @ApplicationContext private val context: Context
) {
    operator fun invoke(id: Int, path: Uri): Name = runCatching<String> {
        val metadataRetriever = MediaMetadataRetriever()
        metadataRetriever.setDataSource(context, path)

        var soundName = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
            ?: return@runCatching "Sound $id"
        soundName = soundName.replace("Ä‡", "ć")
            .replace("Ĺź", "ż")
            .replace("Ĺš", "ś")
            .replace("Ä™", "ę")
            .replace("Ä…", "ą")
            .replace("Ăł", "ó")
        soundName
    }
        .recover {
            "Sound $id"
        }
        .map {
            Name(it)
        }
        .getOrThrow()
}
