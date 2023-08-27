package data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

interface GameObject {
    fun update(realDelta: Float, game: Game)
}