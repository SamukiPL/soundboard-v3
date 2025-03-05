package me.samuki.resource.promotion

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.json.Json
import me.samuki.core.resource.R
import me.samuki.model.Promotion
import me.samuki.resource.promotion.mapper.toDomain
import me.samuki.resource.promotion.model.LocalPromotion
import okio.IOException
import okio.use
import java.io.BufferedReader
import javax.inject.Inject

internal class ResourcePromotionDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) : PromotionLocalDataSource {

    @Throws(IOException::class)
    override suspend fun getPromotion(): List<Promotion> =
        context.resources.openRawResource(R.raw.promotion)
            .bufferedReader()
            .use<BufferedReader, List<LocalPromotion>> {
                Json.decodeFromString(it.readText())
            }.map {
                it.toDomain()
            }
}
