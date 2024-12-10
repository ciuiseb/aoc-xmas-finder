package pattern.visualizer

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*


fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "XMAS",
        state = WindowState(
            size = DpSize(900.dp, 900.dp)
        )
    ) {
        App()
    }
}