package me.samuki.resource.set

import android.net.Uri
import me.samuki.model.Combinable
import me.samuki.model.values.Name
import me.samuki.resource.datastore.SavedUriForFile
import me.samuki.resource.set.steps.ContentValuesBuilder
import me.samuki.resource.set.steps.InsertToResolver
import me.samuki.resource.set.steps.PathsCombiner
import me.samuki.resource.set.steps.SystemSettings
import me.samuki.resource.set.steps.WriteAudioToFile
import me.samuki.resource.set.steps.WriteFileToResolver
import javax.inject.Inject

internal interface Setter {
    suspend fun setCombinables(
        combinablesName: Name,
        ringtoneManagerType: Int,
        vararg combinable: Combinable
    )
}

internal class SetterDelegate @Inject constructor(
    private val pathsCombiner: PathsCombiner,
    private val writeAudioToFile: WriteAudioToFile,
    private val contentValuesBuilder: ContentValuesBuilder,
    private val insertToResolver: InsertToResolver,
    private val savedUriForFile: SavedUriForFile,
    private val writeFileToResolver: WriteFileToResolver,
    private val systemSettings: SystemSettings
) : Setter {

    override suspend fun setCombinables(
        combinablesName: Name,
        ringtoneManagerType: Int,
        vararg combinable: Combinable
    ) {
        // Step 1 - Create paths list
        val paths = pathsCombiner.combinePaths(combinable)
        // Step 2 - Write combinables to file
        val file = writeAudioToFile.writeCombinables(combinablesName, paths)
        // Step 3 - Check Existence and go to step 8 if true
        val uri: Uri = savedUriForFile.getUri(combinablesName) ?: run {
            // Step 4 - Create Content Values
            val contentValues = contentValuesBuilder.build(file, combinablesName)
            // Step 5 - Insert to Resolver -> Returns Uri
            val newUri = insertToResolver.insert(contentValues)
            // Step 6 - Save Uri to Shared Prefs
            savedUriForFile.saveUri(combinablesName, newUri)
            // Step 7 - Write file to resolver
            writeFileToResolver(file, newUri)
            newUri
        }
        // Step 8 - Set to RingtoneManager with returned uri
        systemSettings.setToSettings(ringtoneManagerType, uri)
    }
}
