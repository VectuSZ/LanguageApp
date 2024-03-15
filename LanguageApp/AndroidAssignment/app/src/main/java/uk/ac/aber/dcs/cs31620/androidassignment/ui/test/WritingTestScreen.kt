package uk.ac.aber.dcs.cs31620.androidassignment.ui.test

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import uk.ac.aber.dcs.cs31620.androidassignment.R
import uk.ac.aber.dcs.cs31620.androidassignment.model.AppViewModel
import uk.ac.aber.dcs.cs31620.androidassignment.model.Word
import uk.ac.aber.dcs.cs31620.androidassignment.model.WordsViewModel
import uk.ac.aber.dcs.cs31620.androidassignment.ui.navigation.Screen
import uk.ac.aber.dcs.cs31620.androidassignment.ui.theme.AndroidAssignmentTheme
import kotlin.random.Random

@Composable
fun WritingTestScreen(
    navController: NavHostController,
    wordsViewModel: WordsViewModel = viewModel(),
    appViewModel: AppViewModel = viewModel()
) {
    val wordList by wordsViewModel.wordList.observeAsState(listOf())

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        WritingTestScreenContent(
            modifier = Modifier.padding(8.dp),
            navController = navController,
            wordsList = wordList,
            appViewModel = appViewModel
        )
    }
}

@Composable
fun WritingTestScreenContent(
    modifier: Modifier,
    navController: NavHostController,
    wordsList: List<Word> = listOf(),
    appViewModel: AppViewModel = viewModel()
) {
    var wordTranslation by rememberSaveable { mutableStateOf("") }
    var scoreDialogIsOpen by rememberSaveable { mutableStateOf(false) }
    val numOfQuestion by appViewModel.numOfQuestion.observeAsState(0)
    val correctAnswers by appViewModel.correctAnswers.observeAsState(0)
    val context = LocalContext.current

    if (wordsList.isNotEmpty()) {
        val randomNum by rememberSaveable { mutableStateOf(Random.nextInt(wordsList.size)) }

        ConstraintLayout {
            val (titleText, wordToTranslate, labelWithTranslation, goNextButton, koalaImage) = createRefs()

            Text(
                text = stringResource(id = R.string.write_down_correct_translation),
                lineHeight = 40.sp,
                textAlign = TextAlign.Center,
                fontSize = 35.sp,
                modifier = Modifier
                    .padding(top = 40.dp, start = 20.dp, end = 20.dp)
                    .constrainAs(titleText) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            Image(
                painter = painterResource(id = R.drawable.koala),
                contentDescription = stringResource(id = R.string.koala),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(450.dp)
                    .padding(bottom = 130.dp)
                    .constrainAs(koalaImage) {
                        top.linkTo(wordToTranslate.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            val word: Word = wordsList[randomNum]

            Text(
                text = word.wordInYourLanguage,
                fontSize = 40.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(top = 50.dp, bottom = 40.dp)
                    .constrainAs(wordToTranslate) {
                        top.linkTo(titleText.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )


            TextField(
                value = wordTranslation,
                onValueChange = { wordTranslation = it },
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
                    ){
                        Icon(
                            Icons.Filled.Backspace,
                            contentDescription = stringResource(id = R.string.backspace)
                        )
                    }
                },
                modifier = Modifier
                    .constrainAs(labelWithTranslation) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(koalaImage.bottom)
                        top.linkTo(koalaImage.top)
                    }
            )

            FloatingActionButton(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .constrainAs(goNextButton) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(labelWithTranslation.bottom)
                    },
                onClick = {
                    if ((wordTranslation == "")) {
                        Toast.makeText(context, "Enter answer!", Toast.LENGTH_SHORT).show()
                    } else {

                        appViewModel.increaseNumOfQuestion()
                        if (wordTranslation == word.wordTranslation) {
                            appViewModel.increaseCorrectAnswers()
                        }

                        if (numOfQuestion < 10) {
                            navController.navigate(Screen.WritingTest.route)
                        } else if (numOfQuestion == 10) {
                            scoreDialogIsOpen = true
                            appViewModel.resetNumOfQuestion()
                            appViewModel.resetCorrectAnswers()
                        }
                    }
                },
                containerColor = Color(0xFFAD8886)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowForwardIos,
                    contentDescription = stringResource(R.string.go_forward)
                )
            }
        }
    } else {
        ConstraintLayout {
            val (titleText, koalaImage) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.koala_right_corner),
                contentDescription = stringResource(id = R.string.koala_right_corner_background),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .constrainAs(koalaImage) {
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
            )

            Text(
                text = stringResource(id = R.string.no_words_no_tests),
                lineHeight = 40.sp,
                textAlign = TextAlign.Center,
                fontSize = 35.sp,
                modifier = Modifier
                    .padding(top = 40.dp, start = 20.dp, end = 20.dp)
                    .constrainAs(titleText) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
        }
    }

    ScoreWindow(
        dialogIsOpen = scoreDialogIsOpen,
        dialogOpen = { isScoreOpen ->
            scoreDialogIsOpen = isScoreOpen
        },
        correctAnswers = correctAnswers,
        navController = navController
    )
}

@Preview
@Composable
fun WritingTestScreenPreview() {
    val navController = rememberNavController()
    AndroidAssignmentTheme {
        WritingTestScreen(navController)
    }
}