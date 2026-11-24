package ai.crystal.launcher.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ai.crystal.launcher.ui.theme.CrystalColors

enum class NavItem(val label: String, val icon: ImageVector) {
    Home("Home", Icons.Outlined.Home),
    Instances("Instances", Icons.Outlined.Apps),
    Content("Content", Icons.Outlined.Extension),
    News("News", Icons.Outlined.Newspaper),
    Settings("Settings", Icons.Outlined.Settings)
}

@Composable
fun LauncherShell() {
    var selected by remember { mutableStateOf(NavItem.Home) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CrystalBackgroundBrush())
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        // Title bar equivalent
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(CrystalColors.TitleBar)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(22.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(
                        Brush.linearGradient(
                            listOf(Color(0xFFAA9CFF), Color(0xFF7A68EE))
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Filled.Diamond,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(14.dp)
                )
            }
            Spacer(Modifier.width(10.dp))
            Text(
                "Crystal Launcher",
                color = CrystalColors.Fg,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.width(8.dp))
            Text(
                "2.3.4",
                color = CrystalColors.Dim,
                fontSize = 12.sp
            )
            Spacer(Modifier.width(6.dp))
            Text(
                "Android",
                color = CrystalColors.Accent,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            // Sidebar
            Column(
                modifier = Modifier
                    .width(64.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(16.dp))
                    .background(CrystalColors.Side)
                    .border(1.dp, CrystalColors.Line, RoundedCornerShape(16.dp))
                    .padding(vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Logo button
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(11.dp))
                        .background(
                            Brush.linearGradient(
                                listOf(Color(0xFFAA9CFF), Color(0xFF7A68EE))
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Filled.Diamond,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(Modifier.height(4.dp))

                NavItem.entries.forEach { item ->
                    val isSelected = selected == item
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(11.dp))
                            .background(
                                if (isSelected) CrystalColors.Card2
                                else Color.Transparent
                            )
                            .clickable { selected = item },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            item.icon,
                            contentDescription = item.label,
                            tint = if (isSelected) CrystalColors.Accent else CrystalColors.Muted,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
            }

            Spacer(Modifier.width(12.dp))

            // Main content panel
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(16.dp))
                    .background(CrystalColors.Panel)
                    .border(1.dp, CrystalColors.Line, RoundedCornerShape(16.dp))
                    .padding(20.dp)
            ) {
                when (selected) {
                    NavItem.Home -> HomeScreen()
                    NavItem.Instances -> PlaceholderScreen("Instances", "Your Minecraft instances will appear here.")
                    NavItem.Content -> PlaceholderScreen("Content", "Modrinth + CurseForge (via Cloudflare proxy) browser.")
                    NavItem.News -> PlaceholderScreen("News", "Minecraft patch notes & news.")
                    NavItem.Settings -> PlaceholderScreen("Settings", "RAM, username, loader defaults, accent…")
                }
            }
        }
    }
}

@Composable
private fun CrystalBackgroundBrush() = Brush.linearGradient(
    colors = listOf(CrystalColors.Grad1, CrystalColors.Grad2, CrystalColors.Grad3)
)

@Composable
fun HomeScreen() {
    Column {
        Text(
            "Welcome to Crystal Launcher",
            color = CrystalColors.Fg,
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(Modifier.height(8.dp))
        Text(
            "Android port of v2.3.4 with CurseForge support through your Cloudflare Worker.",
            color = CrystalColors.Text,
            fontSize = 14.sp
        )
        Spacer(Modifier.height(24.dp))

        // Quick info cards
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            InfoCard("Proxy", "Cloudflare Worker active")
            InfoCard("Theme", "Exact desktop colors")
            InfoCard("Status", "Core skeleton ready")
        }
    }
}

@Composable
private fun InfoCard(title: String, subtitle: String) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(14.dp))
            .background(CrystalColors.Card)
            .border(1.dp, CrystalColors.Line, RoundedCornerShape(14.dp))
            .padding(16.dp)
            .widthIn(min = 140.dp)
    ) {
        Text(title, color = CrystalColors.Accent, fontSize = 12.sp, fontWeight = FontWeight.Medium)
        Spacer(Modifier.height(4.dp))
        Text(subtitle, color = CrystalColors.Fg, fontSize = 13.sp)
    }
}

@Composable
fun PlaceholderScreen(title: String, description: String) {
    Column {
        Text(title, color = CrystalColors.Fg, fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.height(8.dp))
        Text(description, color = CrystalColors.Muted, fontSize = 14.sp)
    }
}
