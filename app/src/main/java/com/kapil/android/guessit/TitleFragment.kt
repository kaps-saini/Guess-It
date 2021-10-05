package com.kapil.android.guessit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.kapil.android.guessit.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding:FragmentTitleBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_title, container, false)

        binding.button.setOnClickListener{
            view:View -> Navigation.findNavController(view).navigate(TitleFragmentDirections
            .actionTitleFragmentToGameFragment()) }
        binding.btnAbout.setOnClickListener{
            findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToRulesFragment())
        }
        return binding.root
    }

}