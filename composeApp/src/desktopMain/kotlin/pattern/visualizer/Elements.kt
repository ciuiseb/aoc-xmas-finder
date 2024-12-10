package pattern.visualizer

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableSharedFlow

data class Position(val x: Int, val y: Int)
enum class Directions(val offset: Position) {
    North(Position(0, -1)),
    South(Position(0, 1)),
    West(Position(-1, 0)),
    East(Position(1, 0)),
    SouthEast(Position(1, 1)),
    SouthWest(Position(-1, 1)),
    NorthWest(Position(-1, -1)),
    NorthEast(Position(1, -1))
}

data class CellHighlight(
    val position: Position,
    val color: Color,
    val priority: Int
)

val highlightFlow = MutableSharedFlow<CellHighlight>()

@Composable
fun GridCell(
    content: Char,
    color: Color,
    modifier: Modifier = Modifier
) {
    val animatedColor by animateColorAsState(
        targetValue = color,
        label = "cellColor"
    )

    Box(
        modifier = modifier
            .size(30.dp)
            .background(animatedColor),
        contentAlignment = Alignment.Center
    ) {
        Text(text = content.toString())
    }
}

@Composable
fun Grid(
    grid: List<List<Char>>,
    cellColors: Map<Position, CellHighlight>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        grid.forEachIndexed { rowIndex, row ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                row.forEachIndexed { columnIndex, char ->
                    GridCell(
                        content = char,
                        color = cellColors[Position(columnIndex, rowIndex)]?.color ?: Color.White
                    )
                }
            }
        }
    }
}