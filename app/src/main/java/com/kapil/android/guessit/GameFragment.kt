package com.kapil.android.guessit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.kapil.android.guessit.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentGameBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_game,
        container, false)

        binding.btnQuit.setOnClickListener {
            findNavController().navigate(R.id.action_gameFragment_to_restultFragment)
        }
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        binding.gameViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.score.observe(viewLifecycleOwner, { newScore ->
            binding.txtScoreInGame.text = newScore.toString()
        })

        viewModel.gameHasFinished.observe(viewLifecycleOwner, { isFinished ->
            if (isFinished){
                val currentScore = viewModel.score.value ?: 0
                findNavController().navigate(GameFragmentDirections.actionGameFragmentToRestultFragment(
                    currentScore.toString()
                ))
                Toast.makeText(context, "Game finish", Toast.LENGTH_SHORT).show()
            }
        })
        return binding.root
    }
}