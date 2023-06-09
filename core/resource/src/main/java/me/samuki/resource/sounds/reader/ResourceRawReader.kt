package me.samuki.resource.sounds.reader

import android.net.Uri
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import me.samuki.common.di.DispatcherIO
import me.samuki.core.model.R
import me.samuki.model.values.Id
import me.samuki.model.values.PackageName
import me.samuki.model.values.Path
import me.samuki.resource.sounds.model.ResourceRaw
import java.lang.Integer.min
import javax.inject.Inject

internal class ResourceRawReader @Inject constructor(
    packageName: PackageName,
    @DispatcherIO ioCoroutineContext: CoroutineDispatcher,//TODO clean Coroutines
    private val getSoundName: GetSoundName
) {

    private val soundPath = "android.resource://${packageName.value}/raw/sound"

    private val scope = CoroutineScope(ioCoroutineContext)

    internal val resourceRawFlow = MutableStateFlow<List<ResourceRaw>>(emptyList())

    private val soundsCount: Int = R.raw::class.java.fields.size - 1

    private fun getSoundPath(id: Int) = Uri.parse(soundPath + id)

    init {
        val tmpList = mutableListOf<ResourceRaw>()
        scope.launch {
            val jobs = mutableListOf<Job>()
            (0 until soundsCount step 10).forEach { step ->
                jobs += async {
                    val mutableList = mutableListOf<ResourceRaw>()
                    (step until min((step + 10), soundsCount)).forEach { id ->
                        val soundPath = getSoundPath(id)
                        val soundName = getSoundName(id, soundPath)
                        mutableList.add(
                            ResourceRaw(
                                id = Id(id),
                                name = soundName,
                                path = Path(soundPath)
                            )
                        )
                    }
                    tmpList += (mutableList)
                }
            }
            jobs.forEach { it.join() }
            tmpList.sortBy { model -> model.id.value }
            resourceRawFlow.emit(tmpList.toList())
            scope.cancel()
        }
    }
}
