package uk.ac.aber.dcs.cs31620.androidassignment.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uk.ac.aber.dcs.cs31620.androidassignment.datasource.DictionalaRepository

class WordsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: DictionalaRepository = DictionalaRepository(application)

    var wordList: LiveData<List<Word>> = repository.getAllWords()
        private set

    private fun getWords(){
        wordList = repository.getAllWords()
    }

    fun insertWord(newWord: Word){
        viewModelScope.launch(Dispatchers.IO){
            repository.insert(newWord)
        }
    }

    fun deleteWord(existingWord: Word){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteWord(existingWord)
        }
    }

    fun updateWord(word: Word){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateWord(word)
        }
    }

    fun deleteAll(wordList: List<Word>){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAll(wordList)
        }
    }
}