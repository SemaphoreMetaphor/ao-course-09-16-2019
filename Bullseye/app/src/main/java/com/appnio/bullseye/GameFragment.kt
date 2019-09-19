package com.appnio.bullseye

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_game.*
import kotlin.math.abs
import kotlin.random.Random


class GameFragment : Fragment() {

    companion object {
        fun newInstance() = GameFragment()
    }

    lateinit var preferenceService: PreferenceService

    var currentGoal = 0
    var currentRoundScore = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        preferenceService = PreferenceService(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateTitle()
        updateScore(0)
        updateHighScore()
        submitButton.setOnClickListener {
            calculateScore(seekbar.progress)
            seekbar.progress = 0
            updateTitle()
        }
    }

    private fun calculateScore(guess: Int) {
        val delta = abs(currentGoal - guess)
        if (delta < 5) {
            currentRoundScore += 100 - 10 * delta
        } else {
            currentRoundScore = 0
        }

        updateHighScore()
        updateScore(currentRoundScore)
    }

    private fun updateTitle() {
        currentGoal = getRandomInteger()
        title.text = getString(R.string.title, currentGoal)
    }

    private fun updateScore(score: Int) {
        scoreText.text = getString(R.string.score_text, score)
    }

    private fun updateHighScore() {
        val highScore = preferenceService.getHighScore()
        if (currentRoundScore > highScore) {
            preferenceService.saveHighScore(currentRoundScore)
        }
        highScoreText.text = getString(R.string.high_score_text, preferenceService.getHighScore())
    }

    private fun getRandomInteger() = Random.nextInt(1, 99)
}