package uk.ac.aber.dcs.cs31620.androidassignment.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import uk.ac.aber.dcs.cs31620.androidassignment.ui.theme.AndroidAssignmentTheme
import uk.ac.aber.dcs.cs31620.androidassignment.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPageTopAppBar(
    onClick: () -> Unit = {}
){
    CenterAlignedTopAppBar(
        title = { Text(stringResource(id = R.string.app_name)) },
        navigationIcon = {
            IconButton(onClick = onClick) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = stringResource(R.string.menu)
                )
            }
        }
    )
}


@Preview
@Composable
fun MainPageTopAppBerPreview(){
    AndroidAssignmentTheme() {
        MainPageTopAppBar()
    }
}