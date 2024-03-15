package uk.ac.aber.dcs.cs31620.androidassignment.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class Word(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id: Int = 0,
    var wordInYourLanguage: String,
    var wordTranslation: String,
)
