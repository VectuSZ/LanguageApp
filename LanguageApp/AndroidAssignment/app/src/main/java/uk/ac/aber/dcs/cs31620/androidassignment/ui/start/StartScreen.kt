package uk.ac.aber.dcs.cs31620.androidassignment.ui.start


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import uk.ac.aber.dcs.cs31620.androidassignment.R
import uk.ac.aber.dcs.cs31620.androidassignment.ui.navigation.Screen
import uk.ac.aber.dcs.cs31620.androidassignment.ui.theme.AndroidAssignmentTheme

@Composable
fun StartScreen(
    navController: NavHostController
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        StartScreenContent(modifier = Modifier.padding(8.dp), navController)
    }
}

@Composable
private fun StartScreenContent(modifier: Modifier = Modifier, navController: NavHostController) {

    val fontFamily = FontFamily(Font(R.font.abeezeeregular))

    ConstraintLayout {

        val (startScreenBackground, koalaImage, dictionalaTitle, description, letsGetLearnedButton, dismissButton) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = stringResource(id = R.string.start_screen_background),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(startScreenBackground) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Image(
            painter = painterResource(id = R.drawable.koala),
            contentDescription = stringResource(id = R.string.koala),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(bottom = 260.dp, start = 15.dp)
                .size(450.dp)
                .constrainAs(koalaImage) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = stringResource(id = R.string.dictonala_title),
            fontSize = 38.sp,
            fontFamily = fontFamily,
            modifier = Modifier
                .constrainAs(dictionalaTitle) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    top.linkTo(koalaImage.top)
                }
        )

        Text(
            text = stringResource(id = R.string.the_best_dictionary),
            fontSize = 28.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(start = 50.dp, end = 50.dp)
                .constrainAs(description) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(dictionalaTitle.bottom)
                }
        )

        Button(
            onClick = {
                navController.navigate(Screen.Language.route)
            },
            colors = ButtonDefaults.buttonColors(Color(0xFF40000D)),
            modifier = Modifier
                .padding(top = 25.dp)
                .width(380.dp)
                .height(70.dp)
                .constrainAs(letsGetLearnedButton) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(description.bottom)
                }
        ) {
            Text(
                text = stringResource(id = R.string.lets_get_started_button),
                fontSize = 20.sp,
            )
        }


        Button(
            onClick = {

            },
            colors = ButtonDefaults.buttonColors(Color(0xFFFFFFFF)),
            border = BorderStroke(width = 3.dp, Color(0xFF40000D)),
            modifier = Modifier
                .padding(top = 10.dp)
                .width(380.dp)
                .height(70.dp)
                .constrainAs(dismissButton) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(letsGetLearnedButton.bottom)
                }
        ) {
            Text(
                text = stringResource(id = R.string.dismiss_button),
                fontSize = 20.sp,
                color = Color(0xFF40000D)
            )
        }
    }
}

@Preview
@Composable
fun StartScreenPreview() {
    val navController = rememberNavController()
    AndroidAssignmentTheme {
        StartScreen(navController)
    }
}