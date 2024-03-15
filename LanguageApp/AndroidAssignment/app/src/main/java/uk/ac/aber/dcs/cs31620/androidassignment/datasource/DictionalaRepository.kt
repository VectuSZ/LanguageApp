package uk.ac.aber.dcs.cs31620.androidassignment.datasource

import android.app.Application
import uk.ac.aber.dcs.cs31620.androidassignment.model.Word

class DictionalaRepository(application: Application) {
    private val wordDao = DictionalaRoomDatabase.getDatabase(application)!!.wordDao()

    suspend fun insert(word: Word){
        wordDao.insertSingleWord(word)
    }

    suspend fun insertMultipleWords(words: List<Word>){
        wordDao.insertMultipleWords(words)
    }

    suspend fun updateWord(word: Word){
        wordDao.updateWord(word)
    }

    suspend fun deleteWord(word: Word){
        wordDao.deleteWord(word)
    }

    suspend fun deleteAll(words: List<Word>){
        wordDao.deleteAll()
    }

    fun getAllWords() = wordDao.getAllWords()
}