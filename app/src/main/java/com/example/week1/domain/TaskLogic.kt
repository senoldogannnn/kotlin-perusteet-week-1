package com.example.week1.domain


// Lisätään uus taski listaan
fun addTask(list: List<Task>, task: Task): List<Task> {
    return list + task 
}

// Vaihdetaan onko tehty vai ei
fun toggleDone(list: List<Task>, id: String): List<Task> {
    return list.map {
        if (it.id == id) {
            it.copy(done = !it.done) 
        } else {
            it // ei tehä mitään jos väärä id
        }
    }
}

// Suodatetaan tehdyt tai tekemättömät
fun filterByDone(list: List<Task>, done: Boolean): List<Task> {
    return list.filter { it.done == done }
}

// Järjestetään päivämäärän mukaan
fun sortByDueDate(list: List<Task>): List<Task> {
    return list.sortedBy { it.dueDate }
}
