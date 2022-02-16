package com.example.whacamole.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel :ViewModel() {

    private var _score = MutableLiveData(0)
    val score:MutableLiveData<Int> get() = _score

    private var _topScore = MutableLiveData(0)
    val topScore:MutableLiveData<Int> get() = _topScore

    fun scoreIterator(){
        _score.value = _score.value!! + 1
    }
    fun resetScore(){
        _score.value = 0
    }
    fun setTopScore(){
        _topScore.value = _score.value
    }
}