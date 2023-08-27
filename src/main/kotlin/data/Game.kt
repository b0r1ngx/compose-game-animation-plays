package data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

enum class GameState {
    STOPPED, RUNNING
}

class Game {
    val hero = Hero()
    var gameObjects = mutableStateListOf<GameObject>()
    var gameStatus by mutableStateOf("Let's play!")

    private var prevTime = 0L
    private var gameState by mutableStateOf(GameState.RUNNING)

    init {
        start()
    }

    fun start() {
        gameObjects.add(hero)
        gameState = GameState.RUNNING
        gameStatus = "Good luck!"
    }

    fun update(time: Long) {
        val delta = time - prevTime
        val floatDelta = (delta / 1E8).toFloat()
        prevTime = time

        if (gameState == GameState.STOPPED) return

        for (gameObject in gameObjects) {
            gameObject.update(floatDelta, this)
        }
    }

    private fun endGame() {
        gameState = GameState.STOPPED
        gameStatus = "Better luck next time!"
    }

    private fun winGame() {
        gameState = GameState.STOPPED
        gameStatus = "Congratulations!"
    }
}

