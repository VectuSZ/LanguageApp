package uk.ac.aber.dcs.cs31620.androidassignment.ui.test

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
fun OneOfFourTestScreen(
    navController: NavHostController,
    wordsViewModel: WordsViewModel = viewModel(),
    appViewModel: AppViewModel = viewModel()
) {
    val wordList by wordsViewModel.wordList.observeAsState(listOf())
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        OneOfFourTestScreenContent(
            modifier = Modifier.padding(8.dp),
            navController = navController,
            wordsList = wordList,
            appViewModel = appViewModel
        )
    }
}

@Composable
fun OneOfFourTestScreenContent(
    modifier: Modifier,
    navController: NavHostController,
    wordsList: List<Word> = listOf(),
    appViewModel: AppViewModel = viewModel()
) {
    var scoreDialogIsOpen by rememberSaveable { mutableStateOf(false) }
    val numOfQuestion by appViewModel.numOfQuestion.observeAsState(0)
    val correctAnswers by appViewModel.correctAnswers.observeAsState(0)

    if (wordsList.isNotEmpty()) {
        val answers by remember {
            mutableStateOf(
                generateSequence { Random.nextInt(wordsList.size) }.distinct().take(4).toList()
            )
        }
        val correctAnswer: Word by remember { mutableStateOf(wordsList[answers.random()]) }

        ConstraintLayout {
            val (titleText, word, optionOneButton, optionTwoButton, optionThreeButton, optionFourButton, koalaImage) = createRefs()

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
                text = stringResource(id = R.string.choose_correct_translation),
                textAlign = TextAlign.Center,
                lineHeight = 40.sp,
                fontSize = 35.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .padding(top = 30.dp)
                    .constrainAs(titleText) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )


            Text(
                text = correctAnswer.wordInYourLanguage,
                fontSize = 40.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(top = 30.dp, bottom = 40.dp)
                    .constrainAs(word) {
                        top.linkTo(titleText.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            Button(
                onClick = {
                    appViewModel.increaseNumOfQuestion()
                    if (correctAnswer.wordTranslation == wordsList[answers[0]].wordTranslation) {
                        appViewModel.increaseCorrectAnswers()
                    }

                    if (numOfQuestion < 10) {
                        navController.navigate(Screen.OneOfFourTest.route)
                    } else if (numOfQuestion == 10) {
                        scoreDialogIsOpen = true
                        appViewModel.resetNumOfQuestion()
                    }
                    if (numOfQuestion == 1) {
                        appViewModel.resetCorrectAnswers()
                    }
                },
                colors = ButtonDefaults.buttonColors(Color(0xFFAD8886)),
                modifier = Modifier
                    .padding(top = 20.dp)
                    .width(300.dp)
                    .height(70.dp)
                    .constrainAs(optionOneButton) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(word.bottom)
                    }
            ) {
                Text(
                    text = wordsList[answers[0]].wordTranslation,
                    fontSize = 20.sp,
                    color = Color(0xFF000000)
                )
            }


            Button(
                onClick = {
                    appViewModel.increaseNumOfQuestion()
                    if (correctAnswer.wordTranslation == wordsList[answers[1]].wordTranslation) {
                        appViewModel.increaseCorrectAnswers()
                    }

                    if (numOfQuestion < 10) {
                        navController.navigate(Screen.OneOfFourTest.route)
                    } else if (numOfQuestion == 10) {
                        scoreDialogIsOpen = true
                        appViewModel.resetNumOfQuestion()
                    }
                    if (numOfQuestion == 1) {
                        appViewModel.resetCorrectAnswers()
                    }
                },
                colors = ButtonDefaults.buttonColors(Color(0xFFAD8886)),
                modifier = Modifier
                    .padding(top = 30.dp)
                    .width(300.dp)
                    .height(70.dp)
                    .constrainAs(optionTwoButton) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(optionOneButton.bottom)
                    }
            ) {
                Text(
                    text = wordsList[answers[1]].wordTranslation,
                    fontSize = 20.sp,
                    color = Color(0xFF000000)
                )
            }

            Button(
                onClick = {
                    appViewModel.increaseNumOfQuestion()
                    if (correctAnswer.wordTranslation == wordsList[answers[2]].wordTranslation) {
                        appViewModel.increaseCorrectAnswers()
                    }

                    if (numOfQuestion < 10) {
                        navController.navigate(Screen.OneOfFourTest.route)
                    } else if (numOfQuestion == 10) {
                        scoreDialogIsOpen = true
                        appViewModel.resetNumOfQuestion()
                    }
                    if (numOfQuestion == 1) {
                        appViewModel.resetCorrectAnswers()
                    }
                },
                colors = ButtonDefaults.buttonColors(Color(0xFFAD8886)),
                modifier = Modifier
                    .padding(top = 30.dp)
                    .width(300.dp)
                    .height(70.dp)
                    .constrainAs(optionThreeButton) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(optionTwoButton.bottom)
                    }
            ) {
                Text(
                    text = wordsList[answers[2]].wordTranslation,
                    fontSize = 20.sp,
                    color = Color(0xFF000000)
                )
            }


            Button(
                onClick = {
                    appViewModel.increaseNumOfQuestion()
                    if (correctAnswer.wordTranslation == wordsList[answers[3]].wordTranslation) {
                        appViewModel.increaseCorrectAnswers()
                    }

                    if (numOfQuestion < 10) {
                        navController.navigate(Screen.OneOfFourTest.route)
                    } else if (numOfQuestion == 10) {
                        scoreDialogIsOpen = true
                        appViewModel.resetNumOfQuestion()
                    }
                    if (numOfQuestion == 1) {
                        appViewModel.resetCorrectAnswers()
                    }
                },
                colors = ButtonDefaults.buttonColors(Color(0xFFAD8886)),
                modifier = Modifier
                    .padding(top = 30.dp)
                    .width(300.dp)
                    .height(70.dp)
                    .constrainAs(optionFourButton) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(optionThreeButton.bottom)
                    }
            ) {
                Text(
                    text = wordsList[answers[3]].wordTranslation,
                    fontSize = 20.sp,
                    color = Color(0xFF000000)
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
                textAlign = TextAlign.Center,
                lineHeight = 40.sp,
                fontSize = 35.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .padding(top = 30.dp)
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
fun OneOfFourTestScreenPreview(
) {
    val navController = rememberNavController()
    AndroidAssignmentTheme {
        OneOfFourTestScreen(navController = navController)
    }
}