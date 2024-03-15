package uk.ac.aber.dcs.cs31620.androidassignment.ui.test

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import uk.ac.aber.dcs.cs31620.androidassignment.R
import uk.ac.aber.dcs.cs31620.androidassignment.ui.theme.AndroidAssignmentTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoodAnswerWindow(
    dialogIsOpen: Boolean,
    dialogOpen: (Boolean) -> Unit = { }
) {
    if (dialogIsOpen) {
        Dialog(
            onDismissRequest = {
            },
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
                color = Color(0xFFB9DDB3),
                border = BorderStroke(width = 2.dp, Color(0xFF00000D)),
            ) {

                ConstraintLayout {

                    val (goodJobText, koalaImage, doneButton) = createRefs()

                    Text(
                        text = stringResource(id = R.string.good_answer),
                        fontSize = 38.sp,
                        modifier = Modifier
                            .padding(top = 50.dp)
                            .constrainAs(goodJobText) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    )

                    FloatingActionButton(
                        modifier = Modifier
                            .padding(top = 30.dp)
                            .constrainAs(doneButton) {
                                top.linkTo(goodJobText.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            },
                        onClick = {

                        },
                        containerColor = Color(0xFFB9DDB3)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = stringResource(R.string.done)
                        )
                    }

                    Image(
                        painter = painterResource(id = R.drawable.koala_left_corner),
                        contentDescription = stringResource(id = R.string.koala_left_corner_background),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(200.dp)
                            .constrainAs(koalaImage) {
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                                top.linkTo(doneButton.bottom)
                            }
                    )
                }
            }
        }
    }
}
@Preview
@Composable
fun GoodAnswerWindowPreview(){
    AndroidAssignmentTheme(){
        GoodAnswerWindow(dialogIsOpen = true)
    }
}