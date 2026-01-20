package com.example.week1.domain

data class Task(
    val id: String,
    val title: String,
    val description: String,
    val priority: Priority,
    val dueDate: String,
    val done: Boolean = false
)

enum class Priority {
    HIGH, MEDIUM, LOW
}

val mockTasks = listOf(
    Task(
        id = "1",
        title = "Tee harkat",
        description = "Pakko saada tää viikon 1 harkka valmiiks..",
        priority = Priority.HIGH,
        dueDate = "2026-01-10",
        done = false
    ),
    Task(
        id = "2",
        title = "Kauppareissu",
        description = "Maito, kahvi ja ehkä jotain hyvää iltapalaks",
        priority = Priority.MEDIUM,
        dueDate = "2026-01-11",
        done = true
    ),
    Task(
        id = "3",
        title = "Lue kirjaa",
        description = "Joku 30 sivuu ois jees",
        priority = Priority.LOW,
        dueDate = "2026-01-12",
        done = false
    ),
    Task(
        id = "4",
        title = "Salille",
        description = "Jalkapäivä, ei saa skippaa",
        priority = Priority.MEDIUM,
        dueDate = "2026-01-13",
        done = false
    ),
    Task(
        id = "5",
        title = "Maksa laskut",
        description = "Sähkölasku erääntyy kohta!",
        priority = Priority.HIGH,
        dueDate = "2026-01-14",
        done = true
    ),
    Task(
        id = "6",
        title = "Bisse kaverin kaa",
        description = "Jarnon kaa yhellä kallios",
        priority = Priority.LOW,
        dueDate = "2026-01-15",
        done = false
    )
)
