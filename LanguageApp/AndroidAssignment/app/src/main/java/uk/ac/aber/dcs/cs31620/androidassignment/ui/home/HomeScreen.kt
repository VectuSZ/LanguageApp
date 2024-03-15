package uk.ac.aber.dcs.cs31620.androidassignment.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
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
import uk.ac.aber.dcs.cs31620.androidassignment.ui.components.TopLevelScaffold
import uk.ac.aber.dcs.cs31620.androidassignment.ui.theme.AndroidAssignmentTheme
import kotlin.random.Random

@Composable
fun HomeScreen(
    navController: NavHostController,
    wordsViewModel: WordsViewModel = viewModel(),
    appViewModel: AppViewModel = viewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val wordList by wordsViewModel.wordList.observeAsState(listOf())

    TopLevelScaffold(
        navController = navController,
        coroutineScope = coroutineScope,
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            HomeScreenContent(
                modifier = Modifier.padding(8.dp),
                navController = navController,
                wordsList = wordList,
                appViewModel = appViewModel
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreenContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    wordsList: List<Word> = listOf(),
    appViewModel: AppViewModel = viewModel()
) {
    ConstraintLayout {

        val (welcomeText, infoText, yourLanguageCard, languageYouLearnCard, numOfWordsCard, numOfWords, randomWordCard, koalaImage) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.koala),
            contentDescription = stringResource(id = R.string.koala),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(250.dp)
                .padding(bottom = 30.dp)
                .constrainAs(koalaImage) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = stringResource(id = R.string.welcome_to_dictionala),
            textAlign = TextAlign.Start,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 10.dp, start = 15.dp)
                .constrainAs(welcomeText) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
        )

        Text(
            text = stringResource(id = R.string.home_screen_info),
            textAlign = TextAlign.Start,
            fontSize = 20.sp,
            lineHeight = 25.sp,
            modifier = Modifier
                .padding(15.dp)
                .constrainAs(infoText) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(welcomeText.bottom)
                }
        )

        Card(
            shape = RoundedCornerShape(25.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(10.dp)
                .constrainAs(yourLanguageCard) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(infoText.bottom)
                },
            colors = CardDefaults.cardColors(Color(0xFFC4A296))
        ) {
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Your language is: ")
                    }
                    append(appViewModel.getUserLanguage()!!)
                },
                modifier = Modifier
                    .padding(10.dp),
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
            )
        }

        Card(
            shape = RoundedCornerShape(25.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 5.dp)
                .constrainAs(languageYouLearnCard) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(yourLanguageCard.bottom)
                },
            colors = CardDefaults.cardColors(Color(0xFFC4A296))
        ) {
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Language you learn is: ")
                    }
                    append(appViewModel.getLanguageUserLearning()!!)
                },
                modifier = Modifier
                    .padding(10.dp),
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
            )
        }

        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .width(220.dp)
                .height(150.dp)
                .padding(top = 15.dp, bottom = 10.dp, start = 10.dp, end = 10.dp)
                .constrainAs(numOfWordsCard) {
                    start.linkTo(parent.start)
                    top.linkTo(languageYouLearnCard.bottom)
                    end.linkTo(numOfWords.start)
                },
            colors = CardDefaults.cardColors(Color(0xFFFFFBFF)),
        ) {
            Column(
                modifier = Modifier
                    .width(220.dp)
                    .padding(top = 20.dp, bottom = 20.dp, start = 10.dp, end = 5.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.num_of_words),
                    modifier = Modifier
                        .padding(top = 5.dp, bottom = 5.dp, start = 10.dp, end = 5.dp),
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 20.sp
                )
            }
        }

        if (wordsList.isNotEmpty()) {
            Card(
                shape = RoundedCornerShape(70.dp),
                modifier = Modifier
                    .size(150.dp)
                    .padding(10.dp)
                    .constrainAs(numOfWords) {
                        top.linkTo(languageYouLearnCard.bottom)
                        start.linkTo(numOfWordsCard.end)
                        end.linkTo(parent.end)
                    },
                colors = CardDefaults.cardColors(Color(0xFFC4A296))
            ) {
                Text(
                    text = "${wordsList.size}",
                    fontSize = 50.sp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 25.dp)
                )
            }
        } else {
            Card(
                shape = RoundedCornerShape(70.dp),
                modifier = Modifier
                    .size(150.dp)
                    .padding(10.dp)
                    .constrainAs(numOfWords) {
                        top.linkTo(languageYouLearnCard.bottom)
                        start.linkTo(numOfWordsCard.end)
                        end.linkTo(parent.end)
                    },
                colors = CardDefaults.cardColors(Color(0xFFC4A296))
            ) {
                Text(
                    text = "0",
                    fontSize = 50.sp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 25.dp)
                )
            }
        }

        if (wordsList.isNotEmpty()) {
            val randomNum by remember { mutableStateOf(Random.nextInt(wordsList.size)) }
            Card(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .padding(bottom = 20.dp, start = 10.dp, end = 10.dp)
                    .constrainAs(randomWordCard) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
                colors = CardDefaults.cardColors(Color(0xFFAD8886))
            ) {
                Text(
                    modifier = Modifier
                        .padding(15.dp)
                        .align(Alignment.CenterHorizontally),
                    text = "Random word generator: \n${wordsList[randomNum].wordInYourLanguage} is ${wordsList[randomNum].wordTranslation}",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    lineHeight = 25.sp
                )
            }
        } else {
            Card(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .padding(bottom = 20.dp, start = 10.dp, end = 10.dp)
                    .constrainAs(randomWordCard) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
                colors = CardDefaults.cardColors(Color(0xFFAD8886))
            ) {
                Text(
                    modifier = Modifier
                        .padding(15.dp)
                        .align(Alignment.CenterHorizontally),
                    text = "Random word generator: No words in vocabulary list",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    lineHeight = 25.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    AndroidAssignmentTheme {
        HomeScreen(navController)
    }
}