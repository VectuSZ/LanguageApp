package uk.ac.aber.dcs.cs31620.androidassignment.ui.test


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import uk.ac.aber.dcs.cs31620.androidassignment.R
import uk.ac.aber.dcs.cs31620.androidassignment.ui.navigation.Screen
import uk.ac.aber.dcs.cs31620.androidassignment.ui.theme.AndroidAssignmentTheme

@Composable
fun ScoreWindow(
    dialogIsOpen: Boolean,
    dialogOpen: (Boolean) -> Unit = { },
    correctAnswers: Int,
    navController: NavHostController
) {

    if (dialogIsOpen) {
        Dialog(
            onDismissRequest = {}
        ) {
            Surface(
                modifier = Modifier
                    .height(350.dp)
                    .width(300.dp),
                shape = RoundedCornerShape(
                    topStart = 50.dp,
                    topEnd = 50.dp,
                    bottomStart = 50.dp,
                    bottomEnd = 50.dp
                ),
                border = BorderStroke(width = 2.dp, Color(0xFF00000D)),
            ) {

                ConstraintLayout {

                    val (congratulationText, score, koalaImage, goForwardButton) = createRefs()

                    Text(
                        text = (stringResource(id = R.string.congratulation)),
                        textAlign = TextAlign.Center,
                        lineHeight = 40.sp,
                        fontSize = 32.sp,
                        modifier = Modifier
                            .padding(top = 20.dp, start = 15.dp, end = 15.dp)
                            .constrainAs(congratulationText) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    )

                    Text(
                        text = "$correctAnswers/10",
                        textAlign = TextAlign.Center,
                        fontSize = 32.sp,
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .constrainAs(score) {
                                top.linkTo(congratulationText.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    )

                    FloatingActionButton(
                        modifier = Modifier
                            .padding(top = 230.dp)
                            .constrainAs(goForwardButton) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(score.bottom)
                                bottom.linkTo(koalaImage.top)
                            },
                        onClick = {
                            navController.navigate(Screen.Test.route)
                        },
                        containerColor = Color(0xFFFFFFFF)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowForwardIos,
                            contentDescription = stringResource(R.string.go_forward)
                        )
                    }

                    Image(
                        painter = painterResource(id = R.drawable.koala),
                        contentDescription = stringResource(id = R.string.koala),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(450.dp)
                            .padding(bottom = 140.dp, start = 10.dp)
                            .constrainAs(koalaImage) {
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(goForwardButton.bottom)
                            }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ScoreWindowPreview() {
    val correctAnswers = 7
    val navController = rememberNavController()
    AndroidAssignmentTheme() {
        ScoreWindow(dialogIsOpen = true, {}, correctAnswers, navController)
    }
}