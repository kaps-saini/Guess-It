package com.kapil.android.guessit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kapil.android.guessit.databinding.FragmentRestultBinding

class ResultFragment : Fragment() {
    private lateinit var viewModel: ResultViewModel
    private lateinit var viewModelFactory: ResultViewModelFactory

  override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         val binding:FragmentRestultBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_restult,
             container, false)

        val resultFragmentArgs by navArgs<ResultFragmentArgs>()

      binding.btnHome.setOnClickListener{
          view?.let { it1 -> Navigation.findNavController(it1).navigate(R.id.action_restultFragment_to_titleFragment) }
      }

      binding.btnPlayAgain.setOnClickListener{
          view:View -> Navigation.findNavController(view).navigate(R.id.action_restultFragment_to_gameFragment)
      }
      binding.btnAbout2.setOnClickListener{
          findNavController().navigate(ResultFragmentDirections.actionRestultFragmentToRulesFragment())
      }

      viewModelFactory = ResultViewModelFactory(resultFragmentArgs.gameScore)
      viewModel = ViewModelProviders.of(this, viewModelFactory).get(ResultViewModel::class.java)

      viewModel.score.observe(viewLifecycleOwner, { newScore ->
          binding.txtScore.text = newScore.toString()
      })

      return binding.root
    }
}