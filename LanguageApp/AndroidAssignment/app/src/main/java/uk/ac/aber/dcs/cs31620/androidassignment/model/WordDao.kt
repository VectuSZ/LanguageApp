package uk.ac.aber.dcs.cs31620.androidassignment.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WordDao {
    @Insert
    suspend fun insertSingleWord(word: Word)

    @Insert
    suspend fun insertMultipleWords(wordsList: List<Word>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateWord(word: Word)

    @Delete
    suspend fun deleteWord(word: Word)

    @Query("DELETE FROM words")
    suspend fun deleteAll()

    @Query("SELECT * FROM words")
    fun getAllWords(): LiveData<List<Word>>
}