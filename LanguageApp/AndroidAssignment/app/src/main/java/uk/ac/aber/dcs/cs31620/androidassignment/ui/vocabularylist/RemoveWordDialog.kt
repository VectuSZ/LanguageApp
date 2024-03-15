package uk.ac.aber.dcs.cs31620.androidassignment.ui.vocabularylist


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import uk.ac.aber.dcs.cs31620.androidassignment.R
import uk.ac.aber.dcs.cs31620.androidassignment.model.Word
import uk.ac.aber.dcs.cs31620.androidassignment.model.WordsViewModel

@Composable
fun RemoveWordDialog(
    dialogIsOpen: Boolean,
    dialogOpen: (Boolean) -> Unit = {},
    isRemoved: (Boolean) -> Unit = {}
) {
    if(dialogIsOpen){
        AlertDialog(
            onDismissRequest = {},
            icon = {
                Icon(
                    Icons.Filled.Delete,
                    contentDescription = stringResource(id = R.string.remove)
                )
            },
            title = {
                Text(
                    text = stringResource(id = R.string.remove_word)
                )
            },
            text = {
                Text(
                    text = stringResource(id = R.string.do_you_want_remove)
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        dialogOpen(false)
                    }
                ) {
                    Text(
                        stringResource(id = R.string.confirm_button)
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        dialogOpen(false)
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.dismiss_button)
                    )
                }
            }
        )
    }
}