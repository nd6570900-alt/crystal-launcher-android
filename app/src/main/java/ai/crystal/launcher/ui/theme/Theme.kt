package ai.crystal.launcher.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

private val CrystalDarkScheme = darkColorScheme(
    primary = CrystalColors.Accent,
    onPrimary = CrystalColors.AccentInk,
    secondary = CrystalColors.Accent2,
    background = CrystalColors.Bg,
    surface = CrystalColors.Solid,
    onBackground = CrystalColors.Fg,
    onSurface = CrystalColors.Text,
    error = CrystalColors.Danger,
    outline = CrystalColors.Line
)

/** Desktop-style radial + linear background brush */
val CrystalBackgroundBrush = Brush.linearGradient(
    colors = listOf(
        CrystalColors.Grad1,
        CrystalColors.Grad2,
        CrystalColors.Grad3
    )
)

@Composable
fun CrystalTheme(
    darkTheme: Boolean = true,          // launcher is always dark by default
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = CrystalDarkScheme,
        typography = CrystalTypography,
        content = content
    )
}
