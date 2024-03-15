package uk.ac.aber.dcs.cs31620.androidassignment.ui.components

import android.graphics.Outline
import androidx.compose.ui.graphics.vector.ImageVector

data class IconGroup (
    val filledIcon: ImageVector,
    val outlinedIcon: ImageVector,
    val label: String
    )