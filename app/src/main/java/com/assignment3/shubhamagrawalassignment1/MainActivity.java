package com.assignment3.shubhamagrawalassignment1;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import com.assignment3.shubhamagrawalassignment1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // ViewBinding instance to access UI elements defined in activity_main.xml
    private ActivityMainBinding binding;

    // Handler to schedule and run periodic tasks on the main (UI) thread
    private Handler handler = new Handler();

    // Variable to store the start time (in milliseconds) when the stopwatch starts
    private long startTime = 0L;

    // Variable to store the elapsed time (in milliseconds)
    private long elapsedTime = 0L;

    // Flag to indicate whether the stopwatch is currently running
    private boolean isRunning = false;

    // Runnable that updates the elapsed time on the UI every 50 milliseconds
    private Runnable updateTimeRunnable = new Runnable() {
        @Override
        public void run() {
            // Get the current time in milliseconds since boot, including sleep
            long currentTime = SystemClock.elapsedRealtime();
            // Calculate the time difference from when the stopwatch was started
            long time = currentTime - startTime;
            // Update the TextView with the formatted time (MM:SS:MS)
            binding.elapsedTimeTextView.setText(formatTime(time));
            // Schedule the next update after 50 milliseconds
            handler.postDelayed(this, 50);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the layout using ViewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        // Set the activity's content view to the root of the binding layout
        setContentView(binding.getRoot());

        // Set click listeners for each button in the UI
        binding.startButton.setOnClickListener(this);
        binding.stopButton.setOnClickListener(this);
        binding.resetButton.setOnClickListener(this);

        // Initialize the button states:
        // Only the Start button is enabled at launch;
        // the Stop and Reset buttons are disabled.
        binding.startButton.setEnabled(true);
        binding.stopButton.setEnabled(false);
        binding.resetButton.setEnabled(false);
    }

    // This method handles click events for all three buttons
    @Override
    public void onClick(View v) {
        if (v == binding.startButton) {
            // When the Start button is clicked, start the stopwatch
            startStopwatch();
        } else if (v == binding.stopButton) {
            // When the Stop button is clicked, pause the stopwatch
            stopStopwatch();
        } else if (v == binding.resetButton) {
            // When the Reset button is clicked, reset the stopwatch time
            resetStopwatch();
        }
    }

    // Starts or resumes the stopwatch
    private void startStopwatch() {
        if (!isRunning) {
            // Adjust startTime so that the stopwatch resumes from the previously elapsed time
            startTime = SystemClock.elapsedRealtime() - elapsedTime;
            // Begin posting the updateTimeRunnable to update the elapsed time display
            handler.post(updateTimeRunnable);
            isRunning = true;
            // Update button states: disable Start, enable Stop, and disable Reset while running
            binding.startButton.setEnabled(false);
            binding.stopButton.setEnabled(true);
            binding.resetButton.setEnabled(false);
        }
    }

    // Stops (pauses) the stopwatch
    private void stopStopwatch() {
        if (isRunning) {
            // Remove the update runnable to pause updates to the elapsed time display
            handler.removeCallbacks(updateTimeRunnable);
            // Calculate the elapsed time so far
            elapsedTime = SystemClock.elapsedRealtime() - startTime;
            isRunning = false;
            // Update button states: enable Start and Reset, disable Stop
            binding.startButton.setEnabled(true);
            binding.stopButton.setEnabled(false);
            binding.resetButton.setEnabled(true);
        }
    }

    // Resets the stopwatch to its initial state (zero elapsed time)
    private void resetStopwatch() {
        if (!isRunning) {
            // Set the elapsed time back to zero
            elapsedTime = 0L;
            // Update the elapsed time display to show 00:00:00
            binding.elapsedTimeTextView.setText(formatTime(0));
            // Disable the Reset button until the stopwatch is paused again
            binding.resetButton.setEnabled(false);
        }
    }

    // Converts the given time in milliseconds into a formatted string (MM:SS:MS)
    private String formatTime(long millis) {
        int minutes = (int)(millis / 60000);              // Convert milliseconds to minutes
        int seconds = (int)((millis % 60000) / 1000);       // Remaining seconds after minutes
        int milliseconds = (int)((millis % 1000) / 10);     // Remaining hundredths of a second
        // Return the formatted time as a string (e.g., "01:23:45")
        return String.format("%02d:%02d:%02d", minutes, seconds, milliseconds);
    }

    // Called when the activity is paused; stops updating the stopwatch to save resources
    @Override
    protected void onPause() {
        super.onPause();
        // If the stopwatch is running, remove the update runnable to avoid memory leaks
        if (isRunning) {
            handler.removeCallbacks(updateTimeRunnable);
        }
    }

    // Called when the activity resumes; restarts the update runnable if the stopwatch was running
    @Override
    protected void onResume() {
        super.onResume();
        // If the stopwatch is still running, resume updating the elapsed time display
        if (isRunning) {
            handler.post(updateTimeRunnable);
        }
    }
}
