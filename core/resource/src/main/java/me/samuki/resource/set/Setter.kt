package me.samuki.resource.set

import me.samuki.model.Combinable
import me.samuki.model.values.Name
import me.samuki.resource.set.files.ContentValuesBuilder
import me.samuki.resource.set.files.FileCreator
import me.samuki.resource.set.files.FileExistenceChecker
import me.samuki.resource.set.files.PathsCombiner
import me.samuki.resource.set.files.SaveAudioToFile
import javax.inject.Inject

internal interface Setter {
    fun setCombinables(
        fileName: Name,
        ringtoneManagerType: Int,
        vararg combinable: Combinable
    )
}

internal class SetterDelegate @Inject constructor(
    fileExistenceChecker: FileExistenceChecker,
    fileCreator: FileCreator,
    pathsCombiner: PathsCombiner,
    contentValuesBuilder: ContentValuesBuilder,
    saveAudioToFile: SaveAudioToFile
) : Setter,
    FileExistenceChecker by fileExistenceChecker,
    FileCreator by fileCreator,
    PathsCombiner by pathsCombiner,
    ContentValuesBuilder by contentValuesBuilder,
    SaveAudioToFile by saveAudioToFile {

    override fun setCombinables(
        fileName: Name,
        ringtoneManagerType: Int,
        vararg combinable: Combinable
    ) {
        // Step 1 - Check Existence and go to step 8 if true
        // Step 2 - Create file
        // Step 3 - Create Content Values
        // Step 4 - Insert to Resolver -> Returns Uri
        // Step 5 - Save Uri to Shared Prefs
        // Step 6 - Write to resolver file
        // Step 7 - Update Content Values in resolver
        // Step 8 - Set to RingtoneManager with returned uri
    }
}
