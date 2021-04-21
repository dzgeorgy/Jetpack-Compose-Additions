import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Draws simple paging indicator which consists of given number of dots with spacing between them.
 * @param pages number of pages (total count of dots).
 * @param active active page (-1 to show all as non-active).
 * @param radius radius of each dot in DP. Default: 4dp.
 * @param spacing size of spacing between dots in DP. Default: double radius.
 * @param color color of non-active dots. Default: LocalContentColor with alpha for disabled items.
 * @param activeColor color of active dot. Default: Primary color.
 */
@Composable
fun PagingIndicator(
    pages: Int,
    active: Int,
    radius: Dp = 4.dp,
    spacing: Dp = radius * 2,
    color: Color = LocalContentColor.current.copy(ContentAlpha.disabled),
    activeColor: Color = MaterialTheme.colors.primary,
) {
    //Checking active index for requirements conformity
    require(active in -1 until pages) {
        IndexOutOfBoundsException("Index of active element is out of bounds. Index: $active; Range: -1..${pages - 1}")
    }

    //Diameter multiplied by dots count plus space count between dots (1 less than dots count) multiplied on its own size
    val width = radius * 2 * pages + spacing * (pages - 1)
    //Diameter of dot
    val height = radius * 2

    Canvas(modifier = Modifier.size(width, height)) {
        for (i in 0 until pages)
            drawCircle(color = if (i == active) activeColor else color,
                radius = radius.toPx(),
                //X is size of drawn dots plus size of drawn spaces plus default offset to draw center of first dot not on the edge of the canvas
                //Y is half of the size of canvas (to draw center of the dot in vertical center of the canvas)
                center = Offset((radius * (2 * i + 1) + spacing * i).toPx(),
                    size.height / 2))
    }
}
