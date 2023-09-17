package me.samuki.resource.share

import me.samuki.model.Combinable
import me.samuki.model.values.Name
import me.samuki.resource.file.PathsCombiner
import me.samuki.resource.file.WriteAudioToFile
import javax.inject.Inject

internal class AndroidShareHub @Inject constructor(
    private val pathsCombiner: PathsCombiner,
    private val writeAudioToFile: WriteAudioToFile,
    private val shareFacade: AndroidShareFacade
) : ShareHub {

    override fun shareCombinables(fileName: Name, vararg combinables: Combinable) {
        val paths = pathsCombiner.combinePaths(combinables)
        val shareableFile = writeAudioToFile.writeCombinables(fileName, paths)
        shareFacade.shareSound(shareableFile)
    }
}
