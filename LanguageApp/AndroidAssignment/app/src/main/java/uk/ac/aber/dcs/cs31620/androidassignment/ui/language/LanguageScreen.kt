package uk.ac.aber.dcs.cs31620.androidassignment.ui.language

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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

@Composable
fun LanguageScreen(
    navController: NavHostController,
    languageViewModel: AppViewModel = viewModel(),
    wordsViewModel: WordsViewModel = viewModel()
) {

    val wordList by wordsViewModel.wordList.observeAsState(listOf())

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LanguageScreenContent(
            modifier = Modifier.padding(8.dp),
            navController = navController,
            languageViewModel = languageViewModel,
            wordsViewModel = wordsViewModel,
            wordList = wordList
        )
    }
}

@Composable
private fun LanguageScreenContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    languageViewModel: AppViewModel = viewModel(),
    wordsViewModel: WordsViewModel = viewModel(),
    wordList: List<Word> = listOf()
){

    val yourLanguageValue by languageViewModel.userLanguage.observeAsState("")
    val languageToLearnValue by languageViewModel.languageUserLearning.observeAsState("")
    val context = LocalContext.current

    ConstraintLayout {

        val(languageText, koalaImage, yourLanguageTextField, languageToLearnTextField, goForwardButton) = createRefs()

        Text(
            text = stringResource(id = R.string.languages),
            fontSize = 38.sp,
            modifier = Modifier
                .padding(top = 20.dp)
                .constrainAs(languageText) {
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
                .padding(top = 100.dp)
                .constrainAs(koalaImage) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        TextField(
            value = yourLanguageValue,
            onValueChange = {languageViewModel.changeUserLanguage(it)},
            label = { Text (stringResource(id = R.string.your_language))},
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
                        languageViewModel.clearUserLanguage()
                    }
                ) {
                    Icon(
                        Icons.Filled.Backspace,
                        contentDescription = stringResource(id = R.string.backspace)
                    )
                }
            },
            modifier = Modifier
                .padding(bottom = 75.dp)
                .constrainAs(yourLanguageTextField) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(koalaImage.bottom)
                },
        )

        TextField(
            value = languageToLearnValue,
            onValueChange = { languageViewModel.changeLanguageUserLearning(it)},
            label = { Text (stringResource(id = R.string.language_to_learn))},
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
                        languageViewModel.clearLanguageUserLearning()
                    }
                )
                {
                    Icon(
                        Icons.Filled.Backspace,
                        contentDescription = stringResource(id = R.string.backspace)
                    )
                }
            },
            modifier = Modifier
                .constrainAs(languageToLearnTextField) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(koalaImage.bottom)
                },
        )

        FloatingActionButton(
            modifier = Modifier
                .padding(top = 50.dp)
                .constrainAs(goForwardButton) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(languageToLearnTextField.bottom)
                },
            onClick = {
                if((yourLanguageValue == "" || yourLanguageValue == "Your language") || (languageToLearnValue == "" || languageToLearnValue == "Language to learn")){
                    Toast.makeText(context, "Enter languages!", Toast.LENGTH_SHORT).show()
                } else {
                    deleteWords(
                        wordList = wordList,
                        doDelete = { words ->
                            wordsViewModel.deleteAll(words)
                        }
                    )
                    navController.navigate(Screen.Home.route)
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
}

private fun deleteWords(
    wordList: List<Word> = listOf(),
    doDelete: (List<Word>) -> Unit = {}
){
    if(wordList.isNotEmpty()){
        doDelete(wordList)
    }
}

@Preview
@Composable
fun LanguageScreenPreview(){
    val navController = rememberNavController()
    AndroidAssignmentTheme{
        LanguageScreen(navController)
    }
}