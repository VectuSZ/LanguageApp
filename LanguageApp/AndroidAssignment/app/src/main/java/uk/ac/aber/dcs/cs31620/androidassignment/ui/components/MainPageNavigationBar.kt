package uk.ac.aber.dcs.cs31620.androidassignment.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.ac.aber.dcs.cs31620.androidassignment.ui.navigation.Screen
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import uk.ac.aber.dcs.cs31620.androidassignment.R
import uk.ac.aber.dcs.cs31620.androidassignment.ui.navigation.screens
import uk.ac.aber.dcs.cs31620.androidassignment.ui.theme.AndroidAssignmentTheme

@Composable
fun MainPageNavigationBar(
    navController: NavController
) {
    val icons = mapOf(
        Screen.Home to IconGroup(
            filledIcon = Icons.Filled.Home,
            outlinedIcon = Icons.Outlined.Home,
            label = stringResource(id = R.string.home)
        ),
        Screen.VocabularyList to IconGroup(
            filledIcon = Icons.Filled.MenuBook,
            outlinedIcon = Icons.Outlined.MenuBook,
            label = stringResource(id = R.string.vocabulary_list)
        ),
        Screen.Test to IconGroup(
            filledIcon = Icons.Filled.Quiz,
            outlinedIcon = Icons.Outlined.Quiz,
            label = stringResource(id = R.string.test)
        ),
    )

    NavigationBar(
        containerColor = Color(0xFFAD8886),
        modifier = Modifier
            .graphicsLayer {
                shape = RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp
                )
                clip = true
            }
            ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDesitination = navBackStackEntry?.destination
        screens.forEach { screen ->
            val isSelected = currentDesitination?.hierarchy?.any { it.route == screen.route } == true
            val labelText = icons[screen]!!.label
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = (if (isSelected)
                            icons[screen]!!.filledIcon
                        else
                            icons[screen]!!.outlinedIcon),
                        contentDescription = labelText
                    )
                },
                label = { Text(labelText) },
                selected = isSelected,
                onClick = {
                    navController.navigate(screen.route) {
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun MainPageNavigationBarPreview() {
    val navController = rememberNavController()
    AndroidAssignmentTheme{
        MainPageNavigationBar(navController)
    }
}