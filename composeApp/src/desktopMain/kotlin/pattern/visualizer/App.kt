package pattern.visualizer

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.io.path.Path
import kotlin.io.path.readText

@Composable
fun App() {
    val lines = Path("src/desktopMain/resources/data.txt").readText().trim().lines().map { it.toList() }

    val colorMap = remember { mutableStateMapOf<Position, CellHighlight>() }

    LaunchedEffect(Unit) {
        highlightFlow.collect { highlight ->
            val current = colorMap[highlight.position]
            if (current == null) {
                colorMap[highlight.position] = highlight
            } else {
                if(highlight.priority >= current.priority){
                    colorMap[highlight.position] = highlight

                } else{
                    colorMap[highlight.position] = highlight
                    delay(25)
                    colorMap[highlight.position] = current
                }
            }
        }
    }
    Column(modifier = Modifier.wrapContentSize().padding(16.dp)) {
        Grid(
            grid = lines,
            cellColors = colorMap,
            modifier = Modifier.weight(1f)
        )

        Row(
            modifier = Modifier.padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val scope = rememberCoroutineScope()

            Button(
                onClick = {
                    scope.launch {
                        colorMap.clear()
                        lines.forEachIndexed { rowIndex, row ->
                            row.indices.forEach { columnIndex ->
                                checkPosition(lines, rowIndex, columnIndex)
                            }
                        }
                    }
                }
            ) {
                Text("Find XMAS")
            }


        }
    }
}