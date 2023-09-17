package me.samuki.resource.share

import android.content.Context
import android.content.Intent
import androidx.core.app.ShareCompat
import androidx.core.content.FileProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import me.samuki.core.model.R
import java.io.File
import javax.inject.Inject

internal class AndroidShareFacade @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun shareSound(file: File) {
        val contentUri =
            FileProvider.getUriForFile(context, context.getString(R.string.provider), file)

        ShareCompat.IntentBuilder(context)
            .addStream(contentUri)
            .setChooserTitle(context.getString(R.string.share_text))
            .setType(MIME)
            .createChooserIntent().apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(this)
            }
    }

    private companion object {
        const val MIME = "audio/*"
    }
}
