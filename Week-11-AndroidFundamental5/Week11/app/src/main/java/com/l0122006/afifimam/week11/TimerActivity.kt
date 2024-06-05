package com.l0122006.afifimam.week11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.l0122006.afifimam.week11.databinding.ActivityTimerBinding

class TimerActivity : AppCompatActivity() {

    private lateinit var liveDataTimerViewModel: MainViewModel
    private lateinit var activityTimerBinding: ActivityTimerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityTimerBinding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(activityTimerBinding.root)

        liveDataTimerViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        timesGoing()

        activityTimerBinding.startButton.setOnClickListener {
            liveDataTimerViewModel.startTimer()
        }

        activityTimerBinding.stopButton.setOnClickListener {
            liveDataTimerViewModel.stopTimer()
        }

        activityTimerBinding.resetButton.setOnClickListener {
            liveDataTimerViewModel.resetTimer()
        }

        window.statusBarColor = getColor(android.R.color.transparent)
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.isAppearanceLightStatusBars = true
    }

    private fun timesGoing() {
        val elapsedTimeObserver = Observer<Long> { elapsedSeconds ->
            val hours = elapsedSeconds / 3600
            val minutes = (elapsedSeconds % 3600) / 60
            val seconds = elapsedSeconds % 60
            val formattedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds)
            activityTimerBinding.timeTV.text = formattedTime
        }
        liveDataTimerViewModel.elapsedTime.observe(this, elapsedTimeObserver)
    }
}