# ShubhamAgrawalAssignment1 - Android Wear Stopwatch App

## Overview

This project is an Android Wear application that functions as a simple stopwatch tool. The app allows users to start, stop, and reset the timer, and it is designed to work on small Wear OS screens. The project demonstrates the use of standard Android Wear UI elements, ViewBinding, and listener interfaces implemented at the class level.

## Features

- **Start Stopwatch:**  
  Begins counting time, disables the Start button, and updates the elapsed time every 50 milliseconds.
  
- **Stop Stopwatch:**  
  Pauses the timer, re-enables the Start button, and enables the Reset button.
  
- **Reset Stopwatch:**  
  Resets the elapsed time back to 00:00:00 and disables the Reset button until the stopwatch is stopped again.

## Technical Details

- **Platform:** Wear OS (Android Wear)  
- **Minimum SDK:** API 30 or higher (as required)  
- **Language:** Java  
- **Architecture:** Single-Activity app using ViewBinding  
- **Layout:** ConstraintLayout optimized for small screens  
- **Theme:** Custom theme based on `Theme.AppCompat.Light.NoActionBar`

## Project Structure

- **MainActivity.java:**  
  Contains the logic for the stopwatch. It uses a `Handler` and a `Runnable` to update the elapsed time and implements button click handling using the `View.OnClickListener` interface.
  
- **activity_main.xml:**  
  Defines the user interface, including a TextView for displaying the elapsed time and three Buttons for Start, Stop, and Reset functionalities. The layout is designed using ConstraintLayout to optimize space on a small screen.
  
- **strings.xml & styles.xml:**  
  Contain the string resources (e.g., button labels and default time) and the custom theme for the application.

## Setup & Installation

1. **Download or Clone the Project:**  
   Obtain the project files as provided (either from your version control system or the exported ZIP).

2. **Open in Android Studio:**  
   - Launch Android Studio.  
   - Choose **File > Open** and select the project directory.

3. **Sync Gradle:**  
   Allow Gradle to sync and download all required dependencies.

4. **Run the App:**  
   - Set up a Wear OS emulator (or connect a Wear OS device).  
   - Click the **Run** button in Android Studio to build and deploy the app.

## How to Use

- **Start Button:**  
  Tapping this button will start the stopwatch.
  
- **Stop Button:**  
  Tapping this button pauses the stopwatch.
  
- **Reset Button:**  
  Tapping this button resets the stopwatch to `00:00:00`.

## Contact

For any questions or further assistance regarding this project, please contact:  
**Email:** [sa280337@gmail.com]

---

Thank you for reviewing the ShubhamAgrawalAssignment1 Android Wear Stopwatch App!
