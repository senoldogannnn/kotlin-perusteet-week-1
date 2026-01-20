package com.example.week1.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week1.domain.Task

@Composable
fun HomeScreen(viewModel: TaskViewModel = viewModel()) {
    // Luetaan tasks viewModelista
    val tasks = viewModel.tasks.value
    
    // Tila uudelle tehtävänimelle
    var newTaskTitle by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Mun Tehtävät App (Week 2)", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))
        
        // Lisäys-osio
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = newTaskTitle,
                onValueChange = { newTaskTitle = it },
                label = { Text("Uusi tehtävä") },
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = { 
                    if (newTaskTitle.isNotBlank()) {
                       viewModel.addNewTask(newTaskTitle)
                       newTaskTitle = "" // Tyhjennetään kenttä
                    }
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text("Lisää")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Järjestys ja suodatus napit
        Row {
             Button(onClick = { viewModel.sortTasks() }) {
                 Text("Järjestä Pvm")
             }
             androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(4.dp))
             Button(onClick = { viewModel.showByDone(true) }) {
                 Text("Vain Tehdyt")
             }
             androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(4.dp))
             Button(onClick = { viewModel.resetFilter() }) {
                 Text("Näytä Kaikki")
             }
        }
        
        Spacer(modifier = Modifier.height(8.dp))

        // Listaus
        TaskList(
            tasks = tasks,
            onToggle = { id -> viewModel.toggleTaskDone(id) },
            onDelete = { id -> viewModel.removeTask(id) }
        )
    }
}

@Composable
fun TaskList(
    tasks: List<Task>,
    onToggle: (String) -> Unit,
    onDelete: (String) -> Unit
) {
    LazyColumn {
        items(tasks) { task ->
            TaskItem(task, onToggle, onDelete)
        }
    }
}

@Composable
fun TaskItem(
    task: Task, 
    onToggle: (String) -> Unit, 
    onDelete: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Checkbox vasemmalle
            Checkbox(
                checked = task.done,
                onCheckedChange = { onToggle(task.id) }
            )
            
            // Tekstit keskelle
            Column(modifier = Modifier.weight(1f).padding(start = 8.dp)) {
                Text(text = task.title, fontSize = 20.sp)
                Text(text = "Pvm: ${task.dueDate}")
            }
            
            // Poista-nappi oikealle
            Button(onClick = { onDelete(task.id) }) {
                Text("Poista")
            }
        }
    }
}

