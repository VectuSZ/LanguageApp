package uk.ac.aber.dcs.cs31620.androidassignment.ui.vocabularylist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.layout.getDefaultLazyLayoutKey
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.isPopupLayout
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import uk.ac.aber.dcs.cs31620.androidassignment.R
import uk.ac.aber.dcs.cs31620.androidassignment.model.Word
import uk.ac.aber.dcs.cs31620.androidassignment.model.WordsViewModel
import uk.ac.aber.dcs.cs31620.androidassignment.ui.components.TopLevelScaffold
import uk.ac.aber.dcs.cs31620.androidassignment.ui.navigation.Screen
import uk.ac.aber.dcs.cs31620.androidassignment.ui.theme.AndroidAssignmentTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VocabularyListScreen(
    navController: NavHostController,
    wordsViewModel: WordsViewModel = viewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val wordList by wordsViewModel.wordList.observeAsState(listOf())

    TopLevelScaffold(
        navController = navController,
        coroutineScope = coroutineScope
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            VocabularyListScreenContent(
                modifier = Modifier.padding(8.dp),
                navController = navController,
                wordsList = wordList,
                wordsViewModel = wordsViewModel
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VocabularyListScreenContent(
    modifier: Modifier,
    navController: NavHostController,
    wordsList: List<Word> = listOf(),
    wordsViewModel: WordsViewModel = viewModel()
) {
    val state = rememberLazyListState()
    var addDialogIsOpen by rememberSaveable { mutableStateOf(false) }
    var removeDialogIsOpen by rememberSaveable { mutableStateOf(false)}
//    var isRemoved by rememberSaveable{ mutableStateOf(false)}

    LazyColumn(
        state = state,
        contentPadding = PaddingValues(bottom = 10.dp),
        modifier = Modifier
            .padding(end = 5.dp, bottom = 10.dp)
    ) {
        items(wordsList) {
            Card(
                onClick = {
                },
                modifier = Modifier
                    .height(50.dp)
                    .padding(start = 5.dp, end = 5.dp, top = 5.dp)
                    .fillMaxSize()
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = it.wordInYourLanguage,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .align(Alignment.TopStart)
                    )

                    Text(
                        text = it.wordTranslation,
                        fontSize = 10.sp,
                        modifier = Modifier
                            .padding(start = 10.dp, bottom = 5.dp)
                            .align(Alignment.BottomStart)
                    )

                    IconButton(
                        onClick = {
//                            removeDialogIsOpen = true
//                            if(isRemoved && !removeDialogIsOpen) {
                                deleteWord(
                                    word = it,
                                    doDelete = { existingWord ->
                                        wordsViewModel.deleteWord(existingWord)
                                    }
                                )
//                            }
                        },
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                    ) {
                        Icon(
                            Icons.Filled.Remove,
                            contentDescription = stringResource(id = R.string.remove),
                            modifier = Modifier
                                .size(20.dp)
                        )
                    }
                }
            }
        }
    }

    ConstraintLayout {

        val (addButton) = createRefs()

        FloatingActionButton(
            modifier = Modifier
                .padding(bottom = 10.dp, end = 10.dp)
                .constrainAs(addButton) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                },
            onClick = {
                addDialogIsOpen = true
            },
            containerColor = Color(0xFFAD8886)
        ) {
            Icon(
                Icons.Filled.Add,
                contentDescription = stringResource(id = R.string.add_button),
            )
        }


        AddWordDialog(
            dialogIsOpen = addDialogIsOpen,
            dialogOpen = { isOpen ->
                addDialogIsOpen = isOpen
            },
        )

//        RemoveWordDialog(
//            dialogIsOpen = removeDialogIsOpen,
//            dialogOpen = {isOpen ->
//                removeDialogIsOpen = isOpen
//            },
//            isRemoved = { removed ->
//                isRemoved = removed
//            }
//        )
    }
}

private fun deleteWord(
    word: Word,
    doDelete: (Word) -> Unit = {}
) {
    if (word.wordInYourLanguage.isNotEmpty() && word.wordTranslation.isNotEmpty()) {
        doDelete(word)
    }
}

@Preview
@Composable
fun VocabularyListScreenPreview() {
    val navController = rememberNavController()
    AndroidAssignmentTheme {
        VocabularyListScreen(navController = navController)
    }
}