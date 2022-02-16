package com.example.whacamole.fragments

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.whacamole.R
import com.example.whacamole.databinding.FragmentMainBinding
import com.example.whacamole.databinding.FragmentResultBinding


class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding
    private val viewModel:MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentResultBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.score.text = viewModel.score.value.toString()
        if (viewModel.score.value!! > viewModel.topScore.value!!){
            viewModel.setTopScore()
        }

        binding.playAgainButton.isSoundEffectsEnabled = false
        binding.goHomeButton.isSoundEffectsEnabled = false

        binding.goHomeButton.setOnClickListener {
            MediaPlayer.create(requireActivity(),R.raw.button).start()
            findNavController().navigate(R.id.to_main_fragment)
            viewModel.resetScore()
        }

        binding.playAgainButton.setOnClickListener {
            MediaPlayer.create(requireActivity(),R.raw.button).start()
            findNavController().navigate(R.id.result_to_game)
            viewModel.resetScore()
        }
    }
    companion object {

        @JvmStatic
        fun newInstance() = ResultFragment()

    }
}