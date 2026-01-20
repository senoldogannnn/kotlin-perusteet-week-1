package com.example.week1.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.week1.domain.Priority
import com.example.week1.domain.Task
import com.example.week1.domain.addTask
import com.example.week1.domain.filterByDone
import com.example.week1.domain.mockTasks
import com.example.week1.domain.sortByDueDate
import com.example.week1.domain.toggleDone
import java.util.UUID

class TaskViewModel : ViewModel() {
    // Tehtävälista state (näkyvä lista)
    var tasks = mutableStateOf<List<Task>>(emptyList())
        private set

    // "Tietokanta" muistissa, jotta filtteröinti ei hukkaa tehtäviä
    private var allTasks = mockTasks

    init {
        tasks.value = allTasks
    }

    // Lisää uusi tehtävä
    fun addNewTask(title: String) {
        val newTask = Task(
            id = UUID.randomUUID().toString(),
            title = title,
            description = "",
            priority = Priority.LOW,
            dueDate = "2026-01-20",
            done = false
        )
        // Päivitetään sekä "tietokanta" että näkyvä lista
        allTasks = addTask(allTasks, newTask)
        tasks.value = allTasks
    }

    // Merkitään tehdyksi / ei tehdyksi
    fun toggleTaskDone(id: String) {
        allTasks = toggleDone(allTasks, id)
        tasks.value = allTasks // Palautetaan näkyviin kaikki (tai pitäisi muistaa filtteri, mutta pidetään yksinkertaisena)
    }

    // Poista tehtävä
    fun removeTask(id: String) {
        allTasks = allTasks.filter { it.id != id }
        tasks.value = allTasks
    }

    // Suodata tehdyt/tekemättömät
    fun showByDone(isDone: Boolean) {
        tasks.value = filterByDone(allTasks, isDone)
    }
    
    // Palauta kaikki
    fun resetFilter() {
        tasks.value = allTasks
    }

    // Järjestä pvm mukaan
    fun sortTasks() {
        // Järjestetään nykyinen näkymä
        tasks.value = sortByDueDate(tasks.value)
    }
}
