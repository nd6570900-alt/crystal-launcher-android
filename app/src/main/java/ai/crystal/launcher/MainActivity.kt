package ai.crystal.launcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ai.crystal.launcher.ui.screens.LauncherShell
import ai.crystal.launcher.ui.theme.CrystalBackgroundBrush
import ai.crystal.launcher.ui.theme.CrystalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CrystalTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(CrystalBackgroundBrush)
                ) {
                    LauncherShell()
                }
            }
        }
    }
}
