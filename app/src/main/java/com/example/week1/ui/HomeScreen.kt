package com.example.week1.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week1.domain.Task
import com.example.week1.domain.mockTasks
import com.example.week1.domain.sortByDueDate

@Composable
fun HomeScreen() {
    val tasks = sortByDueDate(mockTasks) 

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Mun Tehtävät App", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))
        
        TaskList(tasks = tasks)
    }
}

@Composable
fun TaskList(tasks: List<Task>) {
    LazyColumn {
        items(tasks) { task ->
            TaskItem(task)
        }
    }
}

@Composable
fun TaskItem(task: Task) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = task.title, fontSize = 20.sp)
            Text(text = task.description)
            Row {
                Text(text = "Pvm: ${task.dueDate}", modifier = Modifier.padding(end = 8.dp))
                // Näytetään onko tehty
                if (task.done) {
                    Text(text = "(TEHTY!)")
                } else {
                    Text(text = "(EI VIELÄ)")
                }
            }
        }
    }
}
