package uk.ac.aber.dcs.cs31620.androidassignment.ui.vocabularylist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.ac.aber.dcs.cs31620.androidassignment.R
import uk.ac.aber.dcs.cs31620.androidassignment.model.Word
import uk.ac.aber.dcs.cs31620.androidassignment.model.WordsViewModel
import uk.ac.aber.dcs.cs31620.androidassignment.ui.theme.AndroidAssignmentTheme


@Composable
fun AddWordDialog(
    wordsViewModel: WordsViewModel = viewModel(),
    dialogIsOpen: Boolean,
    dialogOpen: (Boolean) -> Unit = { },
) {

    var wordInYourLanguage by remember { mutableStateOf("Word") }
    var wordTranslation by remember { mutableStateOf("Word translation") }

    if (dialogIsOpen) {
        Dialog(
            onDismissRequest = {
                dialogOpen(false)
            }
        ) {
            Surface(
                modifier = Modifier
                    .size(350.dp),
                shape = RoundedCornerShape(
                    topStart = 50.dp,
                    topEnd = 50.dp,
                    bottomStart = 50.dp,
                    bottomEnd = 50.dp
                ),
                border = BorderStroke(width = 2.dp, Color(0xFF00000D)),
            ) {
                ConstraintLayout {

                    val (addWordLabel, addTranslationLabel, goForwardButton) = createRefs()


                    TextField(
                        value = wordInYourLanguage,
                        onValueChange = { wordInYourLanguage = it },
                        label = { Text(stringResource(id = R.string.add_word)) },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color(0xFFAD8886),
                            textColor = Color(0xFF000000),
                            unfocusedLabelColor = Color(0xFF000000),
                            focusedLabelColor = Color(0xFF000000),
                            disabledLabelColor = Color(0xFF000000)
                        ),
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    wordInYourLanguage = ""
                                }
                            ) {
                                Icon(
                                    Icons.Filled.Backspace,
                                    contentDescription = stringResource(id = R.string.backspace)
                                )
                            }
                        },
                        modifier = Modifier
                            .padding(top = 50.dp)
                            .constrainAs(addWordLabel) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(parent.top)
                            }
                    )

                    TextField(
                        value = wordTranslation,
                        onValueChange = { wordTranslation = it },
                        label = { Text(stringResource(id = R.string.add_word_translation)) },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color(0xFFAD8886),
                            textColor = Color(0xFF000000),
                            unfocusedLabelColor = Color(0xFF000000),
                            focusedLabelColor = Color(0xFF000000),
                            disabledLabelColor = Color(0xFF000000)
                        ),
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    wordTranslation = ""
                                }
                            ) {
                                Icon(
                                    Icons.Filled.Backspace,
                                    contentDescription = stringResource(id = R.string.backspace)
                                )
                            }
                        },
                        modifier = Modifier
                            .padding(top = 40.dp)
                            .constrainAs(addTranslationLabel) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(addWordLabel.bottom)
                            }
                    )

                    FloatingActionButton(
                        modifier = Modifier
                            .padding(top = 50.dp)
                            .constrainAs(goForwardButton) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(addTranslationLabel.bottom)
                            },
                        onClick = {
                            insertWord(
                                wordInYourLanguage = wordInYourLanguage,
                                wordTranslation = wordTranslation,
                                doInsert = { newWord ->
                                    wordsViewModel.insertWord(newWord)
                                }
                            )
                            dialogOpen(false)


                        },
                        containerColor = Color(0xFFAD8886)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowForwardIos,
                            contentDescription = stringResource(R.string.go_forward)
                        )
                    }
                }
            }
        }
    }
}

private fun insertWord(
    wordInYourLanguage: String,
    wordTranslation: String,
    doInsert: (Word) -> Unit = {}
){
    if(wordInYourLanguage.isNotEmpty() && wordTranslation.isNotEmpty()){
        val word = Word(
            id = 0,
            wordInYourLanguage = wordInYourLanguage,
            wordTranslation = wordTranslation
        )
        doInsert(word)
    }
}

@Preview
@Composable
fun DistanceDialogPreview(){
    AndroidAssignmentTheme(){
        AddWordDialog(dialogIsOpen = true)
    }
}