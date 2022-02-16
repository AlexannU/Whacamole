package com.example.whacamole.fragments

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.view.get
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.whacamole.R
import com.example.whacamole.databinding.FragmentGameBinding
import com.example.whacamole.databinding.FragmentMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random


class GameFragment : Fragment() {
    private lateinit var binding: FragmentGameBinding
    private val viewModel:MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        for(i in   1..9){
            val moleButton = layoutInflater.inflate(R.layout.grid_item,binding.gridButtons,false)
            moleButton.isEnabled = false
            moleButton.isSoundEffectsEnabled = false
            moleButton.setOnClickListener {
                viewModel.scoreIterator()
                MediaPlayer.create(requireActivity(),R.raw.smash2).start()
                moleButton.isEnabled = false
            }
            binding.gridButtons.addView(moleButton)
        }

        viewModel.score.observe(requireActivity()){
            binding.score.text = it.toString()
        }

        object : CountDownTimer(30000, 1000) {
            val timerSound = MediaPlayer.create(requireActivity(),R.raw.timer)
            override fun onTick(millisUntilFinished: Long) {
                println(millisUntilFinished)
                if (millisUntilFinished in 9001..9999){
                    timerSound.start()
                }
                if (millisUntilFinished / 1000 >= 10){
                    binding.timer.text = "0:" + millisUntilFinished / 1000
                } else binding.timer.text = "0:0" + millisUntilFinished / 1000
            }

            override fun onFinish() {
                timerSound.stop()
                MediaPlayer.create(requireActivity(),R.raw.bell).start()
                findNavController().navigate(R.id.to_result_fragment)
            }
        }.start()

        CoroutineScope(Dispatchers.Main).launch {
            repeat(50){
                val randomIndex = Random.nextInt(1,9)
                binding.gridButtons[randomIndex].isEnabled = true
                delay(600L)
                binding.gridButtons[randomIndex].isEnabled = false
            }
        }



    }
    companion object {

        @JvmStatic
        fun newInstance() = GameFragment()

    }
}