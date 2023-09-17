package me.samuki.resource.share

import me.samuki.model.Combinable
import me.samuki.model.values.Name

public interface ShareHub {

    public fun shareCombinables(fileName: Name, vararg combinables: Combinable)
}
