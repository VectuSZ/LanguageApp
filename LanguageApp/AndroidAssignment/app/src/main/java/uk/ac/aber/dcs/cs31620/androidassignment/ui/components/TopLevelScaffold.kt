package uk.ac.aber.dcs.cs31620.androidassignment.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopLevelScaffold(
    navController: NavHostController,
    coroutineScope: CoroutineScope,
    pageContent: @Composable (innerPadding: PaddingValues) -> Unit = { }
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    MainPageNavigationDrawer(
        navController,
        drawerState = drawerState,
        closeDrawer = {
            coroutineScope.launch {
                // We know it will be open
                drawerState.close()
            }
        }
    ) {
        Scaffold(
            topBar = {
                MainPageTopAppBar(onClick = {
                    coroutineScope.launch {
                        if (drawerState.isOpen) {
                            drawerState.close()
                        } else {
                            drawerState.open()
                        }
                    }
                })
            },
            bottomBar = {
                MainPageNavigationBar(navController)
            },
            content = { innerPadding ->
                pageContent(innerPadding)
            }
        )
    }
}