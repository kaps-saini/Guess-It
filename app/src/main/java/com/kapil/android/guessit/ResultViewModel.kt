package com.kapil.android.guessit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResultViewModel(finalScore: String): ViewModel() {

    private var _score = MutableLiveData<String>()
    val score : LiveData<String>
    get() = _score

    init {
        _score.value = finalScore
        Log.i("ScoreViewModel", "Final score is $finalScore")
    }
}