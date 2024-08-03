import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
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

                Toggle(
                    text = "Rotation",
                    checked = rotationEnabled,
                    onCheckedChange = { rotationEnabled = it },
                )
                Toggle(
                    text = "Position",
                    checked = positionEnabled,
                    onCheckedChange = { positionEnabled = it },
                )
            }
        }
    }
}

@Composable
private fun Toggle(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text)

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colors.primary,
                checkedTrackColor = MaterialTheme.colors.primary.copy(alpha = 0.5f),
            )
        )
    }
}