package me.samuki.model.serialization

import androidx.core.net.toUri
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import me.samuki.model.values.Path

public class PathSerializer : KSerializer<Path> {
    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("Path", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Path {
        val pathUri = decoder.decodeString().toUri()
        return Path(
            value = pathUri
        )
    }

    override fun serialize(encoder: Encoder, value: Path) {
        encoder.encodeString(value.value.toString())
    }
}
