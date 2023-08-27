import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import compose.Hero
import data.Game
import data.Hero
import data.HeroState
import java.awt.SystemColor.text

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    application {
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
            state = rememberWindowState(size = DpSize(200.dp, 200.dp)),
            onKeyEvent = {
//                if (it.type == KeyEventType.KeyDown) {
                when(it.type) {
                    KeyEventType.KeyDown -> {
                        when(it.key) {
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

                            Key.S -> {
                                println("S")
                                game.hero.move(HeroState.CROUCH_IDLE)
                                true
                            }
                        }
                        false
                    }

                    KeyEventType.KeyUp -> {
                        game.hero.move(HeroState.IDLE)
                        true
                    }
                    else -> false
                }

//                when (it.key) {
//                    Key.A -> {
//                        println("A")
//                        game.hero.move(HeroState.MOVE_LEFT)
//                        true
//                    }
//
//                    Key.D -> {
//                        println("D")
//                        game.hero.move(HeroState.MOVE_RIGHT)
//                        true
//                    }
//
//                    Key.Spacebar -> {
//                        game.hero.move(HeroState.JUMP)
//                        true
//                    }
//
//                    Key.W -> {
//                        game.hero.move(HeroState.JUMP)
//                        true
//                    }
//
//                    Key.S -> {
//                        game.hero.move(HeroState.CROUCH)
//                        true
//                    }
//
//                    else -> {
////                            println("IDLE 1")
////                            game.hero.move(HeroState.IDLE)
//                        false
//                    }
//                }
//                } else {
//                    game.hero.move(HeroState.IDLE)
//                    false
//                }
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
