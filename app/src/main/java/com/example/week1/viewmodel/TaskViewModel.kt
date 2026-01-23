package com.example.week1.viewmodel


import androidx.lifecycle.ViewModel
import com.example.week1.model.Priority
import com.example.week1.model.Task
import com.example.week1.model.mockTasks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

class TaskViewModel : ViewModel() {
    private val _tasks = MutableStateFlow<List<Task>>(mockTasks)
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    fun addTask(title: String, description: String) {
        val newTask = Task(
            id = UUID.randomUUID().toString(),
            title = title,
            description = description,
            priority = Priority.LOW,
            dueDate = "2026-01-20",
            done = false
        )
        _tasks.update { it + newTask }
    }

    fun toggleDone(task: Task) {
        _tasks.update { currentTasks ->
            currentTasks.map {
                if (it.id == task.id) it.copy(done = !it.done) else it
            }
        }
    }

    fun removeTask(task: Task) {
        _tasks.update { currentTasks ->
            currentTasks.filter { it.id != task.id }
        }
    }

    fun updateTask(task: Task, newTitle: String, newDescription: String) {
        _tasks.update { currentTasks ->
            currentTasks.map {
                if (it.id == task.id) it.copy(title = newTitle, description = newDescription) else it
            }
        }
    }
}
