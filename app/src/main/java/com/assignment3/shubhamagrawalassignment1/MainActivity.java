package com.assignment3.shubhamagrawalassignment1;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import com.assignment3.shubhamagrawalassignment1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;
    private Handler handler = new Handler();
    private long startTime = 0L;
    private long elapsedTime = 0L;
    private boolean isRunning = false;

    private Runnable updateTimeRunnable = new Runnable() {
        @Override
        public void run() {
            long currentTime = SystemClock.elapsedRealtime();
            long time = currentTime - startTime;
            binding.elapsedTimeTextView.setText(formatTime(time));
            handler.postDelayed(this, 50);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.startButton.setOnClickListener(this);
        binding.stopButton.setOnClickListener(this);
        binding.resetButton.setOnClickListener(this);

        binding.startButton.setEnabled(true);
        binding.stopButton.setEnabled(false);
        binding.resetButton.setEnabled(false);
    }

    @Override
    public void onClick(View v) {
        if (v == binding.startButton) {
            startStopwatch();
        } else if (v == binding.stopButton) {
            stopStopwatch();
        } else if (v == binding.resetButton) {
            resetStopwatch();
        }
    }

    private void startStopwatch() {
        if (!isRunning) {
            startTime = SystemClock.elapsedRealtime() - elapsedTime;
            handler.post(updateTimeRunnable);
            isRunning = true;
            binding.startButton.setEnabled(false);
            binding.stopButton.setEnabled(true);
            binding.resetButton.setEnabled(false);
        }
    }

    private void stopStopwatch() {
        if (isRunning) {
            handler.removeCallbacks(updateTimeRunnable);
            elapsedTime = SystemClock.elapsedRealtime() - startTime;
            isRunning = false;
            binding.startButton.setEnabled(true);
            binding.stopButton.setEnabled(false);
            binding.resetButton.setEnabled(true);
        }
    }

    private void resetStopwatch() {
        if (!isRunning) {
            elapsedTime = 0L;
            binding.elapsedTimeTextView.setText(formatTime(0));
            binding.resetButton.setEnabled(false);
        }
    }

    private String formatTime(long millis) {
        int minutes = (int)(millis / 60000);
        int seconds = (int)((millis % 60000) / 1000);
        int milliseconds = (int)((millis % 1000) / 10);
        return String.format("%02d:%02d:%02d", minutes, seconds, milliseconds);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isRunning) {
            handler.removeCallbacks(updateTimeRunnable);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isRunning) {
            handler.post(updateTimeRunnable);
        }
    }
}