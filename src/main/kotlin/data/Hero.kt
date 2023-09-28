package data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.key.Key

class Hero: GameObject {
    private val idleFrames = mutableListOf(
        "PixelChar2D/Sprite Sheets/hero/idle/image_part_001.png",
        "PixelChar2D/Sprite Sheets/hero/idle/image_part_002.png",
        "PixelChar2D/Sprite Sheets/hero/idle/image_part_003.png",
        "PixelChar2D/Sprite Sheets/hero/idle/image_part_004.png",
        "PixelChar2D/Sprite Sheets/hero/idle/image_part_005.png",
        "PixelChar2D/Sprite Sheets/hero/idle/image_part_006.png",
        "PixelChar2D/Sprite Sheets/hero/idle/image_part_007.png",
        "PixelChar2D/Sprite Sheets/hero/idle/image_part_008.png"
    )
    private val runFrames = mutableListOf(
        "PixelChar2D/Sprite Sheets/hero/run/image_part_001.png",
        "PixelChar2D/Sprite Sheets/hero/run/image_part_002.png",
        "PixelChar2D/Sprite Sheets/hero/run/image_part_003.png",
        "PixelChar2D/Sprite Sheets/hero/run/image_part_004.png",
        "PixelChar2D/Sprite Sheets/hero/run/image_part_005.png",
        "PixelChar2D/Sprite Sheets/hero/run/image_part_006.png",
        "PixelChar2D/Sprite Sheets/hero/run/image_part_007.png",
        "PixelChar2D/Sprite Sheets/hero/run/image_part_008.png",
    )
    private val jumpFrames = mutableListOf(
        "PixelChar2D/Sprite Sheets/hero/jump/image_part_001.png",
        "PixelChar2D/Sprite Sheets/hero/jump/image_part_002.png",
        "PixelChar2D/Sprite Sheets/hero/jump/image_part_003.png",
    )
    private val crouchIdleFrames = mutableListOf(
        "PixelChar2D/Sprite Sheets/hero/crouch/idle/image_part_001.png",
        "PixelChar2D/Sprite Sheets/hero/crouch/idle/image_part_002.png",
        "PixelChar2D/Sprite Sheets/hero/crouch/idle/image_part_003.png",
        "PixelChar2D/Sprite Sheets/hero/crouch/idle/image_part_004.png",
        "PixelChar2D/Sprite Sheets/hero/crouch/idle/image_part_005.png",
        "PixelChar2D/Sprite Sheets/hero/crouch/idle/image_part_006.png",
        "PixelChar2D/Sprite Sheets/hero/crouch/idle/image_part_007.png",
        "PixelChar2D/Sprite Sheets/hero/crouch/idle/image_part_008.png",
        "PixelChar2D/Sprite Sheets/hero/crouch/idle/image_part_009.png",
        "PixelChar2D/Sprite Sheets/hero/crouch/idle/image_part_010.png"
    )
    private val crouchWalkFrames = mutableListOf(
        "PixelChar2D/Sprite Sheets/hero/crouch/walk/image_part_001.png",
        "PixelChar2D/Sprite Sheets/hero/crouch/walk/image_part_002.png",
        "PixelChar2D/Sprite Sheets/hero/crouch/walk/image_part_003.png",
        "PixelChar2D/Sprite Sheets/hero/crouch/walk/image_part_004.png",
        "PixelChar2D/Sprite Sheets/hero/crouch/walk/image_part_005.png",
        "PixelChar2D/Sprite Sheets/hero/crouch/walk/image_part_006.png",
        "PixelChar2D/Sprite Sheets/hero/crouch/walk/image_part_007.png",
        "PixelChar2D/Sprite Sheets/hero/crouch/walk/image_part_008.png",
        "PixelChar2D/Sprite Sheets/hero/crouch/walk/image_part_009.png",
        "PixelChar2D/Sprite Sheets/hero/crouch/walk/image_part_010.png"
    )
    private var lastFrameIndex = 0

    var painter by mutableStateOf(runFrames.first())
    var state by mutableStateOf(HeroState.IDLE)
    var inAir by mutableStateOf(false)

    // hero have 8 parts, lets try to it be in 1 second
    private var totalDelta = 0f
    private val updateTime = 1/2f // 1/8 too fast

    override fun update(realDelta: Float, game: Game) {
        val frames = when(state) {
            HeroState.IDLE -> idleFrames
            HeroState.MOVE_LEFT -> runFrames
            HeroState.MOVE_RIGHT -> runFrames
            HeroState.JUMP -> jumpFrames
            HeroState.CROUCH_IDLE -> crouchIdleFrames
            HeroState.CROUCH_MOVE_LEFT -> crouchIdleFrames
            HeroState.CROUCH_MOVE_RIGHT -> crouchIdleFrames
            HeroState.LAND -> TODO()
        }
        update(realDelta, game, frames)
    }

    private fun update(realDelta: Float, game: Game, frames: List<String>) {
        totalDelta += realDelta
        if (totalDelta >= updateTime) {
            totalDelta = 0f
            lastFrameIndex++
            if (lastFrameIndex == frames.size) lastFrameIndex = 0
            painter = frames[lastFrameIndex]
        }
    }

    fun move(newState: HeroState) {
        if (state != newState) {
            lastFrameIndex = 0
            state = newState
        }
    }
}