package com.appnio.guessinggame

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlin.math.abs
import kotlin.random.Random


class GameFragment : Fragment() {

    companion object {
        fun newInstance(): GameFragment {
            return GameFragment()
        }
    }

    lateinit var preferenceProvider: PreferenceProvider

    lateinit var seekbar: SeekBar
    lateinit var title: TextView
    lateinit var scoreText: TextView
    lateinit var submit: Button
    lateinit var highScore: TextView

    var currentGoal: Int = 0
    var score = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        preferenceProvider = PreferenceProvider(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        currentGoal = generateRandomNumber()
        val view = inflater.inflate(R.layout.fragment_game, container, false)
        seekbar = view.findViewById(R.id.guessing_seekbar)
        title = view.findViewById(R.id.title)
        scoreText = view.findViewById(R.id.score_text)
        submit = view.findViewById(R.id.submit_button)
        highScore = view.findViewById(R.id.high_score)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        resetRound(true)
        submit.setOnClickListener {
            calculateScore(seekbar.progress)
            updateScore()
            resetRound(false)
        }
    }

    private fun calculateScore(guess: Int) {
        Toast.makeText(context, "You guessed $guess", Toast.LENGTH_SHORT).show()
        val delta = abs(currentGoal - guess)
        val scoreFromRound = if (delta < 5) (100 - delta * 10) else 0
        if (scoreFromRound > 0) {
            score += scoreFromRound
            updateHighScore(score)
        } else {
            score = 0
        }
    }

    private fun generateRandomNumber() = Random.nextInt(1, 99)

    private fun updateScore() {
        scoreText.text = getString(R.string.score, score)
    }

    private fun updateCurrentGoal() {
        title.text = getString(R.string.guessing_title, currentGoal)
    }

    private fun updateHighScore(score: Int) {
        val currentHighScore = preferenceProvider.getHighScore()
        if (score > currentHighScore) {
            preferenceProvider.saveHighScore(score)
        }
        highScore.text = getString(R.string.high_score, currentHighScore)
    }

    private fun resetRound(clearScore: Boolean) {
        currentGoal = generateRandomNumber()
        updateCurrentGoal()
        seekbar.progress = 0
        if (clearScore) {
            score = 0
            updateScore()
        }
        updateHighScore(score)
    }
}