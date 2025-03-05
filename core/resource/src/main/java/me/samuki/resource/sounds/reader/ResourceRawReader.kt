package me.samuki.resource.sounds.reader

import android.net.Uri
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import me.samuki.common.di.DispatcherDefault
import me.samuki.core.resource.R
import me.samuki.model.values.Id
import me.samuki.model.values.PackageName
import me.samuki.model.values.Path
import me.samuki.resource.sounds.model.ResourceRaw
import java.lang.Integer.min
import javax.inject.Inject
import androidx.core.net.toUri

internal class ResourceRawReader @Inject constructor(
    packageName: PackageName,
    @DispatcherDefault coroutineDispatcher: CoroutineDispatcher,
    private val getSoundName: GetSoundName
) {

    private val soundPath = "android.resource://${packageName.value}/raw/sound"

    private val scope = CoroutineScope(coroutineDispatcher)

    internal val resourceRawFlow = MutableStateFlow<List<ResourceRaw>>(emptyList())

    private val soundsCount: Int = R.raw::class.java.fields.size - 2

    private fun getSoundPath(id: Int) = (soundPath + id).toUri()

    init {
        scope.launch {
            (0 until soundsCount step 10).map { step ->
                async {
                    (step until min((step + 10), soundsCount)).map { id ->
                        val soundPath = getSoundPath(id)
                        val soundName = getSoundName(id, soundPath)
                        ResourceRaw(
                            id = Id(id),
                            packageName = packageName,
                            name = soundName,
                            path = Path(soundPath)
                        )
                    }
                }
            }
                .awaitAll()
                .flatten()
                .sortedBy { model -> model.id.value }
                .run {
                    resourceRawFlow.emit(this.toList())
                    scope.cancel()
                }
        }
    }
}
