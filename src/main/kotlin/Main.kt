import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.type
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*

import compose.Hero
import data.Game
import data.Hero
import data.HeroState

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    application {
        val windowState = rememberWindowState(
            placement = WindowPlacement.Floating,
            isMinimized = false,
            position = WindowPosition.PlatformDefault,
            size = DpSize(200.dp, 200.dp)
        )

        val game = remember { Game() }
        LaunchedEffect(Unit) {
            while (true) {
                withFrameNanos {
                    game.update(it)
                }
            }
        }

        Window(
            onCloseRequest = ::exitApplication,
            state = windowState,
            onKeyEvent = {
                when (it.type) {
                    KeyEventType.KeyDown -> {
                        when (it.key) {
                            Key.A -> {
                                println("A")
                                game.hero.move(HeroState.MOVE_LEFT)
                                true
                            }

                            Key.D -> {
                                println("D")
                                game.hero.move(HeroState.MOVE_RIGHT)
                                true
                            }

                            Key.Spacebar -> {
                                println("Spacebar")
                                game.hero.move(HeroState.JUMP)
                                true
                            }

                            Key.W -> {
                                println("W")
                                game.hero.move(HeroState.JUMP)
                                true
                            }

                            Key.S -> {
                                println("S")
                                game.hero.move(HeroState.CROUCH_IDLE)
                                true
                            }

                            else -> {
                                false
                            }
                        }
                    }

                    KeyEventType.KeyUp -> {
                        game.hero.move(HeroState.IDLE)
                        true
                    }

                    else -> false
                }
            }
        ) {
            Box(
                modifier = Modifier
                    .background(color = Color.Black)
                    .fillMaxSize()
            ) {
                game.gameObjects.forEach {
                    when (it) {
                        is Hero -> Hero(it)
                    }
                }
            }
        }
    }
}
