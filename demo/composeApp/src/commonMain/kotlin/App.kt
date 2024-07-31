import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ttllab.quake_compose.core.QuakeContent
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import quake_compose.demo.composeapp.generated.resources.Res
import quake_compose.demo.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        var rotationEnabled by remember { mutableStateOf(true) }
        var positionEnabled by remember { mutableStateOf(true) }

        QuakeContent(
            rotationEnabled = rotationEnabled,
            positionEnabled = positionEnabled,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Quake Compose Demo")
                }

                Spacer(Modifier.height(20.dp))

                Button(
                    onClick = { rotationEnabled = !rotationEnabled },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (rotationEnabled) MaterialTheme.colors.primary
                        else {
                            MaterialTheme.colors.secondary
                        }
                    ),
                ) {
                    Text((if (rotationEnabled) "Disable" else "Enable") + " Rotation")
                }
                Button(
                    onClick = { positionEnabled = !positionEnabled },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (positionEnabled) MaterialTheme.colors.primary
                        else {
                            MaterialTheme.colors.secondary
                        }
                    ),
                ) {
                    Text((if (positionEnabled) "Disable" else "Enable") + " Position")
                }
            }
        }
    }
}