package uk.ac.aber.dcs.cs31620.androidassignment.ui.navigation

sealed class Screen(
    val route: String
) {
    object Start : Screen("start")
    object Home : Screen("home")
    object Language : Screen("language")
    object Test : Screen("test")
    object VocabularyList : Screen("vocabulary list")
    object OneOfFourTest : Screen("one of four test")
    object WritingTest : Screen("writing test")
}

val screens = listOf(
    Screen.Home,
    Screen.Test,
    Screen.VocabularyList
)