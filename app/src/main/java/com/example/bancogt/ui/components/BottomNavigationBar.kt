package com.example.bancogt.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Cached
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Payment
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.bancogt.ui.theme.BluePrimary
import com.example.bancogt.ui.theme.WhiteBackground

data class NavItem(
    val label: String,
    val icon: ImageVector,
    val badgeCount: Int? = null
)

@Composable
fun BottomNavigationBar() {
    var selectedIndex by remember { mutableIntStateOf(0) }

    val items = listOf(
        NavItem("Inicio", Icons.Filled.Home),
        NavItem("Pagos", Icons.Outlined.Payment),
        NavItem("Transferir", Icons.Outlined.Cached),
        NavItem("Menú", Icons.Outlined.Menu)
    )

    NavigationBar(
        containerColor = WhiteBackground
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = { selectedIndex = index },
                icon = {
                    if (item.badgeCount != null) {
                        BadgedBox(badge = { Badge { Text("${item.badgeCount}") } }) {
                            Icon(
                                item.icon,
                                contentDescription = item.label,
                                tint = BluePrimary // Íconos en azul corporativo
                            )
                        }
                    } else {
                        Icon(
                            item.icon,
                            contentDescription = item.label,
                            tint = BluePrimary
                        )
                    }
                },
                label = {
                    Text(
                        item.label,
                        color = BluePrimary
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true, name = "BottomNavigationBar Preview")
@Composable
private fun BottomNavigationBarPreview() {
    MaterialTheme {
        Scaffold(
            bottomBar = { BottomNavigationBar() }
        ) { innerPadding ->
            Box(
                modifier = Modifier.fillMaxSize().padding(innerPadding)
            )
        }
    }
}
