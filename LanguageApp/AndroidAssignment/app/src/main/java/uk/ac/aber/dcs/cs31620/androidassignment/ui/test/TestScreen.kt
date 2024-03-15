package uk.ac.aber.dcs.cs31620.androidassignment.ui.test

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import uk.ac.aber.dcs.cs31620.androidassignment.R
import uk.ac.aber.dcs.cs31620.androidassignment.ui.components.TopLevelScaffold
import uk.ac.aber.dcs.cs31620.androidassignment.ui.navigation.Screen
import uk.ac.aber.dcs.cs31620.androidassignment.ui.theme.AndroidAssignmentTheme

@Composable
fun TestScreen(
    navController: NavHostController
) {
    val coroutineScope = rememberCoroutineScope()

    TopLevelScaffold(
        navController = navController,
        coroutineScope = coroutineScope
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            TestScreenContent(modifier = Modifier.padding(8.dp), navController)
        }
    }
}

@Composable
fun TestScreenContent(modifier: Modifier, navController: NavHostController){

    ConstraintLayout{
        val(testText, oneOfFourTestButton, writingTestButton, koalaImage) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.koala),
            contentDescription = stringResource(id = R.string.koala),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(450.dp)
                .padding(top = 230.dp)
                .constrainAs(koalaImage){
                    bottom.linkTo(writingTestButton.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = stringResource(id = R.string.test_yourself),
            fontSize = 38.sp,
            modifier = Modifier
                .padding(top = 30.dp)
                .constrainAs(testText) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Button(
            onClick = {
                navController.navigate(Screen.OneOfFourTest.route)
            },
            colors = ButtonDefaults.buttonColors(Color(0xFFAD8886)),
            modifier = Modifier
                .padding(top = 100.dp)
                .width(320.dp)
                .height(70.dp)
                .constrainAs(oneOfFourTestButton){
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(testText.bottom)
                }
        ) {
            Text(
                text = stringResource(id = R.string.one_of_four),
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF000000)
            )

            Icon(
                Icons.Filled.DensitySmall,
                contentDescription = stringResource(id = R.string.one_of_four_test_icon),
                modifier = Modifier
                    .padding(start = 20.dp)
                    .height(50.dp),
                tint = Color(0xFF000000)
            )
        }

        Button(
            onClick = {
                navController.navigate(Screen.WritingTest.route)
            },
            colors = ButtonDefaults.buttonColors(Color(0xFFAD8886)),
            modifier = Modifier
                .padding(top = 200.dp)
                .width(320.dp)
                .height(70.dp)
                .constrainAs(writingTestButton){
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(oneOfFourTestButton.bottom)
                }
        ) {
            Text(
                text = stringResource(id = R.string.writing_test),
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF000000)
            )

            Icon(
                Icons.Filled.Edit,
                contentDescription = stringResource(id = R.string.writing_test_icon),
                modifier = Modifier
                    .padding(start = 20.dp)
                    .height(50.dp),
                tint = Color(0xFF000000)
            )
        }
    }
}

@Preview
@Composable
fun TestScreenPreview(){
    val navController = rememberNavController()
    AndroidAssignmentTheme{
        TestScreen(navController)
    }
}