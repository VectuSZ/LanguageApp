package uk.ac.aber.dcs.cs31620.androidassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uk.ac.aber.dcs.cs31620.androidassignment.model.AppViewModel
import uk.ac.aber.dcs.cs31620.androidassignment.model.WordsViewModel
import uk.ac.aber.dcs.cs31620.androidassignment.ui.navigation.Screen
import uk.ac.aber.dcs.cs31620.androidassignment.ui.start.StartScreen
import uk.ac.aber.dcs.cs31620.androidassignment.ui.theme.AndroidAssignmentTheme
import uk.ac.aber.dcs.cs31620.androidassignment.ui.home.HomeScreen
import uk.ac.aber.dcs.cs31620.androidassignment.ui.language.LanguageScreen
import uk.ac.aber.dcs.cs31620.androidassignment.ui.test.OneOfFourTestScreen
import uk.ac.aber.dcs.cs31620.androidassignment.ui.test.TestScreen
import uk.ac.aber.dcs.cs31620.androidassignment.ui.test.WritingTestScreen
import uk.ac.aber.dcs.cs31620.androidassignment.ui.vocabularylist.VocabularyListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidAssignmentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BuildNavigationGraph()
                }
            }
        }
    }
}

@Composable
private fun BuildNavigationGraph(
    wordsViewModel: WordsViewModel = viewModel(),
    appViewModel: AppViewModel = viewModel()
){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Start.route
    ) {
        composable(Screen.Start.route) { StartScreen(navController) }
        composable(Screen.Language.route) { LanguageScreen(navController, appViewModel, wordsViewModel) }
        composable(Screen.Home.route) { HomeScreen(navController, wordsViewModel, appViewModel) }
        composable(Screen.Test.route) { TestScreen(navController) }
        composable(Screen.VocabularyList.route) { VocabularyListScreen(navController, wordsViewModel) }
        composable(Screen.OneOfFourTest.route) { OneOfFourTestScreen(navController, wordsViewModel, appViewModel) }
        composable(Screen.WritingTest.route) { WritingTestScreen(navController, wordsViewModel, appViewModel)}
    }
}
