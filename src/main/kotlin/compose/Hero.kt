package compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import data.Hero
import data.HeroState

@Composable
fun Hero(hero: Hero) {
    if (hero.state == HeroState.MOVE_LEFT) {
        Image(
            painter = painterResource(hero.painter),
            contentDescription = null,
            modifier = Modifier
                .size(128.dp)
                .graphicsLayer {
                    rotationY = 180f
                }
        )
    } else {
        Image(
            painter = painterResource(hero.painter),
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )
    }
}