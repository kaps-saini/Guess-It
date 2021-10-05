package com.kapil.android.guessit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ResultViewModelFactory(private var finalScore: String): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultViewModel::class.java)){
            return ResultViewModel(finalScore) as T
            }
        throw IllegalArgumentException("Unknown error occur")
    }
}