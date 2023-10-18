package me.samuki.resource.set.steps

import android.content.Context
import android.media.RingtoneManager
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class SystemSettings @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun setToSettings(
        ringtoneManagerType: Int,
        ringtoneUri: Uri
    ) {
        RingtoneManager.setActualDefaultRingtoneUri(context, ringtoneManagerType, ringtoneUri)
    }
}
