package com.ajs.abetteru.ui.comman

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.MenuBook
import androidx.compose.material.icons.outlined.Healing
import androidx.compose.material.icons.outlined.Square
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import com.ajs.abetteru.R
import com.ajs.abetteru.ui.theme.ABetterUTheme

internal data class NavBarModel(
    @StringRes val title: Int,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector? = null,
    val route: String = "",
    val isVisible: Boolean = true
)

@Composable
fun BottomAppBarState(
    modifier: Modifier = Modifier,
    backStackEntry: State<NavBackStackEntry?> = remember { mutableStateOf(null) },
    onClick: (route: String, isActive: Boolean) -> Unit = { _, _ -> },
    onAddLinkClick: () -> Unit = {},
    isSelectionViewActive: Boolean = false,
) {
    val navigationItems = listOf(
        NavBarModel(
            title = R.string.Journal,
            selectedIcon = Icons.AutoMirrored.Rounded.MenuBook,
//            route = NavigationRoutes.HomeScreen.route,
            isVisible = !isSelectionViewActive
        ),
        NavBarModel(
            title = R.string.Affirmation,
            selectedIcon = Icons.Outlined.Healing,
//            route = NavigationRoutes.ArchiveOrDeleteScreen.route + "?request=${ScreenType.ARCHIVE.value}"
        ),
        NavBarModel(
            title = R.string.VisionBoard,
            selectedIcon = Icons.Outlined.Square,
//            route = NavigationRoutes.ArchiveOrDeleteScreen.route + "?request=${ScreenType.DELETE.value}"
        ),
    )
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            modifier = Modifier
                .padding(end = 8.dp)
                .weight(1f)
                .then(modifier)
                .background(
                    color = MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp),
                    shape = RoundedCornerShape(percent = 100)
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            navigationItems.forEach { item ->
                val selected = item.route == backStackEntry.value?.destination?.route
                NavBarItem(
                    navItem = item,
                    isSelected = selected,
                    onClick = {
                        onClick(it, isSelectionViewActive)
                    },
                )
            }
        }
    }
}

@Composable
internal fun RowScope.NavBarItem(
    navItem: NavBarModel,
    isSelected: Boolean,
    onClick: (route: String) -> Unit,
) {
    val mutableInteraction = remember { MutableInteractionSource() }
    val selectedColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.secondaryContainer else Color.Transparent,
        label = "selectedColor"
    )
    val selectedIconColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onSurfaceVariant,
        label = "selectedIconColor"
    )
    AnimatedVisibility(
        visible = navItem.isVisible,
        modifier = Modifier.weight(1f)
    ) {
        Box(
            modifier = Modifier
                .height(64.dp)
                // Dummy clickable to intercept clicks from passing under the container
                .clickable(
                    indication = null,
                    interactionSource = mutableInteraction,
                    onClick = {}
                ),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .height(32.dp)
                    .width(64.dp)
                    .background(
                        color = selectedColor,
                        shape = RoundedCornerShape(percent = 100)
                    )
                    .clip(RoundedCornerShape(100))
                    .clickable { if (!isSelected) onClick(navItem.route) },
            )
            Icon(
                modifier = Modifier
                    .size(22.dp),
                imageVector = navItem.selectedIcon,
                contentDescription = "navItem.title",
                tint = selectedIconColor
            )
//            val context = LocalContext.current
//            Text(
//                text = context.getString(navItem.title),
//                style = MaterialTheme.typography.bodyMedium,
//                color = selectedIconColor,
//                modifier = Modifier
//                    .align(Alignment.BottomCenter)
//            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomAppBarStatePreview() {
    ABetterUTheme {
        BottomAppBarState()
    }
}