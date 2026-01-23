# Mobiiliohjelmointi - Week 3: MVVM & Jetpack Compose

This project demonstrates the **Model-View-ViewModel (MVVM)** architectural pattern using Jetpack Compose and StateFlow.

## MVVM Architecture
MVVM separates the application into three layers to improve maintainability and testability:

1.  **Model (`com.example.week1.model`)**:
    - Represents the data and business logic.
    - `Task.kt`: Data class defining the structure of a task.
    - `TaskLogic.kt`: Helper functions for data manipulation.

2.  **View (`com.example.week1.view`)**:
    - Displays the UI and reacts to user actions.
    - `HomeScreen.kt`: Main screen displaying the list of tasks.
    - `DetailDialog.kt`: Dialog for editing or deleting a task.
    - The View **observes** the ViewModel's state and updates automatically.

3.  **ViewModel (`com.example.week1.viewmodel`)**:
    - Acts as a bridge between Model and View.
    - `TaskViewModel.kt`: Holds the UI state (`StateFlow`) and handles user intents (e.g., adding or removing tasks).
    - Ensures the UI logic is separated from the UI components.

## StateFlow
**StateFlow** is a state-holder observable flow that emits the current and new state updates to its collectors.

- In `TaskViewModel`, we expose a `StateFlow<List<Task>>` to the View.
- The View uses `collectAsState()` to subscribe to updates.
- When the list changes in the ViewModel, the UI automatically recomposes to reflect the new state.

## Features
- **Add Task**: Create new tasks with a title and optional description.
- **Edit/Delete**: Click on a task to open a dialog where you can edit its details or delete it.
 - **Toggle Done**: Checkbox to mark tasks as completed.
- **Reactive UI**: Updates happen instantaneously thanks to StateFlow.
