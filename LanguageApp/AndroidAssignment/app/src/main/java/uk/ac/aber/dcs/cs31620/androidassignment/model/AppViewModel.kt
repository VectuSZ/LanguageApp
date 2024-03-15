package uk.ac.aber.dcs.cs31620.androidassignment.model

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uk.ac.aber.dcs.cs31620.androidassignment.datasource.DictionalaRepository

class AppViewModel : ViewModel() {
    private val _userLanguage = MutableLiveData("Your language")
    private val _languageUserLearning = MutableLiveData("Language to learn")
    private val _numOfQuestion = MutableLiveData(0)
    private val _correctAnswers = MutableLiveData(0)
    val userLanguage: LiveData<String> = _userLanguage
    val languageUserLearning: LiveData<String> = _languageUserLearning
    val numOfQuestion: LiveData<Int> = _numOfQuestion
    val correctAnswers: LiveData<Int> = _correctAnswers

    fun changeUserLanguage(newUserLanguage: String) {
        _userLanguage.value = newUserLanguage
    }

    fun changeLanguageUserLearning(newLanguageUserLearning: String) {
        _languageUserLearning.value = newLanguageUserLearning
    }

    fun clearUserLanguage(){
        _userLanguage.value = ""
    }

    fun clearLanguageUserLearning(){
        _languageUserLearning.value = ""
    }

    fun increaseNumOfQuestion(){
        _numOfQuestion.value = numOfQuestion.value?.plus(1)
    }

    fun increaseCorrectAnswers(){
        _correctAnswers.value = correctAnswers.value?.plus(1)
    }

    fun resetNumOfQuestion(){
        _numOfQuestion.value = 0
    }

    fun resetCorrectAnswers(){
        _correctAnswers.value = 1
    }

    fun getUserLanguage(): String? {
        return userLanguage.value
    }

    fun getLanguageUserLearning(): String? {
        return  languageUserLearning.value
    }
}