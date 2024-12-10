package pattern.visualizer

import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay

suspend fun checkXMAS(input: List<List<Char>>, row: Int, column: Int) {
    return Directions.entries.map { it.offset }.forEach { (rowOffset, columnOffset) ->
        runCatching {
            val positions = mutableListOf<Position>()
            val candidate = buildString {
                for (i in 0 until 4) {
                    val pos = Position(column + i * columnOffset, row + i * rowOffset)
                    positions.add(pos)
                    append(input[pos.y][pos.x])
                }
            }

            positions.forEach { pos ->
                highlightFlow.emit(CellHighlight(pos, Color(0xFFE0FFFF), 1))
                delay(25)
            }

            if (candidate != "XMAS" && candidate != "XMAS".reversed()) {
                positions.forEach { pos ->
                    highlightFlow.emit(CellHighlight(pos, Color.Red, 3))
                    delay(25)
                }
                positions.forEach { pos ->
                    highlightFlow.emit(CellHighlight(pos, Color.LightGray, 3))
                    delay(25)
                }
            } else {
                positions.forEach { pos ->
                    highlightFlow.emit(CellHighlight(pos, Color.Cyan, 10))
                    delay(25)
                }
            }
        }
    }
}

suspend fun checkPosition(input: List<List<Char>>, row: Int, column: Int) {
    val char = input[row][column]

    highlightFlow.emit(CellHighlight(Position(column, row), Color.DarkGray, 3))
    delay(200)
    if (char == 'X') {
        checkXMAS(input, row, column)
    } else {
        highlightFlow.emit(CellHighlight(Position(column, row), Color.LightGray, 3))
        delay(50)

    }

}