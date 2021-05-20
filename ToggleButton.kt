import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import java.util.*

@Composable
fun <E : Enum<E>> ToggleButton(
    items: Array<E>,
    active: Int = 0,
    onActiveChange: (value: E, index: Int) -> Unit = { _, _ -> }
) {
    Row {
        items.forEachIndexed { index, item ->
            var colors: ButtonColors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colors.onSurface
                    .copy(alpha = ContentAlpha.disabled)
            )
            var stroke: BorderStroke = ButtonDefaults.outlinedBorder
            if (index == active) {
                colors = ButtonDefaults.outlinedButtonColors()
                stroke =
                    BorderStroke(ButtonDefaults.OutlinedBorderSize, MaterialTheme.colors.primary)
            }
            val shape = when (index) {
                0 -> MaterialTheme.shapes.small.copy(
                    topEnd = CornerSize(0.dp),
                    bottomEnd = CornerSize(0)
                )
                items.size - 1 -> MaterialTheme.shapes.small.copy(
                    topStart = CornerSize(0.dp),
                    bottomStart = CornerSize(0.dp)
                )
                else -> MaterialTheme.shapes.small.copy(CornerSize(0.dp))
            }
            OutlinedButton(
                onClick = { onActiveChange(item, index) },
                shape = shape,
                colors = colors,
                border = stroke,
                modifier = Modifier
                    .absoluteOffset(x = (-1).dp * index)
                    .zIndex(if (index == active) 1f else 0f)
            ) {
                Text(
                    text = item.toString().toUpperCase(Locale.getDefault()),
                    style = MaterialTheme.typography.button
                )
            }
        }
    }
}
