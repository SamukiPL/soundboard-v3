package me.samuki.resource.provider

import android.net.Uri
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import me.samuki.common.di.DispatcherIO
import me.samuki.core.model.R
import me.samuki.model.values.Id
import me.samuki.model.values.PackageName
import me.samuki.model.values.Path
import me.samuki.resource.model.ResourceSound
import java.lang.Integer.min
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ResourceSoundsProvider @Inject constructor(
    packageName: PackageName,
    @DispatcherIO ioCoroutineContext: CoroutineDispatcher,
    private val getSoundName: GetSoundName
) : SoundsProvider {

    private val soundPath = "android.resource://${packageName.value}/raw/sound"

    private val scope = CoroutineScope(ioCoroutineContext)

    override val soundFlow = MutableSharedFlow<List<ResourceSound>>(1)

    private val soundsCount: Int by lazy {
        R.raw::class.java.fields.size - 1
    }

    private fun getSoundPath(id: Int) = Uri.parse(soundPath + id)

    init {
        val tmpList = mutableListOf<ResourceSound>()
        scope.launch {
            val jobs = mutableListOf<Job>()
            (0 until soundsCount step 10).forEach { step ->
                jobs += async {
                    val mutableList = mutableListOf<ResourceSound>()
                    (step until min((step + 10), soundsCount)).forEach { id ->
                        val soundPath = getSoundPath(id)
                        val soundName = getSoundName(id, soundPath)
                        mutableList.add(
                            ResourceSound(
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
            soundFlow.emit(tmpList.toList())
            scope.cancel()
        }
    }
}
