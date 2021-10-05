package com.kapil.android.guessit

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.view.View
import androidx.lifecycle.*
import androidx.navigation.Navigation

class GameViewModel:ViewModel() {

    companion object {
        // These represent different important times
        // This is when the game is over
        const val DONE = 0L
        // This is the number of milliseconds in a second
        const val ONE_SECOND = 1000L
        // This is the total time of the game
        const val COUNTDOWN_TIME = 60000L
    }
    private var _word = MutableLiveData<String>()
    val word: LiveData<String>
    get() = _word

    private var _score = MutableLiveData<Int>()
    val score: LiveData<Int>
    get() = _score

    private var _gameHasFinished = MutableLiveData<Boolean>()
    val gameHasFinished: LiveData<Boolean>
    get() = _gameHasFinished

    private lateinit var wordList :MutableList<String>

    private lateinit var timer: CountDownTimer

    private var _currentTimer = MutableLiveData<Long>()
    val currentTimer : LiveData<Long>
    get() = _currentTimer

    var currentTimeString = Transformations.map(currentTimer) { time ->
        DateUtils.formatElapsedTime(time)
    }

    init {
        _gameHasFinished.value = false
        _score.value = 0
        _word.value = ""
        resetList()
        updateWord()

      timer =  object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
               _currentTimer.value =(millisUntilFinished / ONE_SECOND)
            }

            override fun onFinish() {
                _currentTimer.value = DONE
              _gameHasFinished.value = true
            }
        }
          timer.start()
    }

    private fun resetList() {
          wordList = mutableListOf(
             "Chair","Table","Door","Car","Train","Bus","Cycle","Airplane","Sky","Black","Bat","Ball","Football",
             "Water","River","Actor","Music","School","Teacher","Chopper","Rice","Burger","Pizza","Restaurant",
              "Soap","Soccer","Cricket","Dog","Cat","Computer","Rat","Elephant","Star","Sun","Iron","Maths","Heart",
              "save",
              "whistle",
              "church",
              "guard",
              "weak",
              "change",
              "sharp",
              "itchy",
              "sniff",
              "bumpy",
              "color",
              "oceanic",
              "rhyme",
              "thing",
              "lean",
              "responsible",
              "injure",
              "head",
              "unaccountable",
              "deceive",
              "free",
              "realize",
              "battle",
              "knowledge",
              "soggy",
              "loaf",
              "tick",
              "dizzy",
              "pause",
              "bead",
              "inject",
              "question",
              "willing",
              "trap",
              "swim",
              "year",
              "long",
              "verdant",
              "chickens",
              "mushy",
              "needy",
              "argue",
              "stuff",
              "sheep",
              "painful",
              "children",
              "nutty",
              "cover",
              "blue-eyed",
              "thread",
              "impulse",
              "ask",
              "unused",
              "woozy",
              "moldy",
              "intend",
              "eyes",
              "prose",
              "wink",
              "command",
              "camp",
              "toothpaste",
              "ritzy",
              "fluttering",
              "colorful",
              "sick",
              "minute",
              "accidental",
              "quilt",
              "gainful",
              "embarrass",
              "improve",
              "key",
              "hilarious",
              "rat",
              "ladybug",
              "adjoining",
              "proud",
              "sack",
              "jazzy",
              "geese",
              "hang",
              "plan",
              "unruly",
              "available",
              "clover",
              "neck",
              "limit",
              "green",
              "decorous",
              "plain",
              "plot",
              "gray",
              "wind",
              "taboo",
              "damage",
              "follow",
              "squealing",
              "ready",
                      "plausible"
         )
        wordList.shuffle()
     }

    private fun updateWord(){
        if (wordList.isEmpty()){
            resetList()
        }else{
            _word.value = wordList.removeAt(0)
        }
    }

     fun next(){
         updateWord()
        _score.value = score.value?.plus(10)
    }
     fun skip(){
         updateWord()
        _score.value = score.value?.minus(10)
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }
}
