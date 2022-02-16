package com.example.whacamole

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var backgroundMusic:MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        backgroundMusic = MediaPlayer.create(this,R.raw.background)
        backgroundMusic.isLooping = true
        backgroundMusic.setVolume(0.1f,0.1f)
        backgroundMusic.start()

    }
    override fun onStop() {
        super.onStop()
        Log.d("State","stop")
        backgroundMusic.stop()

    }

    override fun onRestart() {
        super.onRestart()
        backgroundMusic = MediaPlayer.create(this,R.raw.background)
        backgroundMusic.isLooping = true
        backgroundMusic.setVolume(0.1f,0.1f)
        backgroundMusic.start()
    }
}