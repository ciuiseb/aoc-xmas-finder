# XMAS Pattern Finder Visualization
A visualization tool for the Advent of Code 2023, Day 4 "Ceres Search" puzzle, built with Kotlin and Compose Desktop.

## Problem Description
From Advent of Code 2023, Day 4 challenges you to find all occurrences of "XMAS" in a word search grid. The interesting twist is:
- Words can be horizontal, vertical, or diagonal
- Words can be written forwards or backwards
- Words can overlap with other occurrences

## Features
- Interactive grid visualization of the word search
- Real-time visualization of the search process
  - Dark Gray: Currently being checked
  - Light gray: Checked, no pattern found yet
  - Red flash: Checked pattern that didn't match
  - Cyan: Found XMAS pattern

## Demo
https://github.com/user-attachments/assets/2fc2ceae-e313-41f4-bdda-a286657cfd48

## Technical Implementation
- Built with Kotlin Multiplatform (Desktop target)
- Uses Jetpack Compose for Desktop UI
- Features animated color transitions
- Uses coroutines for smooth search visualization

## How to Run
1. Clone the repository
2. Make sure you have JDK installed
3. Run the application:
   ```bash
   ./gradlew run
