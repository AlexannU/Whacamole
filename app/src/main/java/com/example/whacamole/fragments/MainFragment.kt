package com.example.whacamole.fragments

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.whacamole.R
import com.example.whacamole.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel:MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.playButton.isSoundEffectsEnabled = false
        binding.topScore.text = viewModel.topScore.value.toString()
        binding.playButton.setOnClickListener {
            MediaPlayer.create(requireActivity(),R.raw.button).start()
            findNavController().navigate(R.id.to_game_fragment)
        }
    }


    companion object {

        @JvmStatic
        fun newInstance() = MainFragment()

    }
}