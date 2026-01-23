package com.example.week1.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week1.model.Task
import com.example.week1.viewmodel.TaskViewModel

@Composable
fun HomeScreen(viewModel: TaskViewModel = viewModel()) {
    // Luetaan tasks viewModelista StateFlown kautta
    val tasks by viewModel.tasks.collectAsState()
    
    // Tila dialogille
    var showDialog by remember { mutableStateOf(false) }
    var selectedTask by remember { mutableStateOf<Task?>(null) }
    
    // Tila uudelle tehtävänimelle
    var newTaskTitle by remember { mutableStateOf("") }
    var newTaskDescription by remember { mutableStateOf("") }

    if (showDialog && selectedTask != null) {
        DetailDialog(
            task = selectedTask!!,
            onDismiss = { showDialog = false },
            onSave = { title, desc ->
                viewModel.updateTask(selectedTask!!, title, desc)
                showDialog = false
            },
            onDelete = {
                viewModel.removeTask(selectedTask!!)
                showDialog = false
            }
        )
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp).statusBarsPadding()) {
        Text(text = "Mun Tehtävät App (Week 3 - MVVM)", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))
        
        // Lisäys-osio
        Column(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = newTaskTitle,
                onValueChange = { newTaskTitle = it },
                label = { Text("Uusi tehtävä") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
             OutlinedTextField(
                value = newTaskDescription,
                onValueChange = { newTaskDescription = it },
                label = { Text("Kuvaus (Valinnainen)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { 
                    if (newTaskTitle.isNotBlank()) {
                       viewModel.addTask(newTaskTitle, newTaskDescription)
                       newTaskTitle = "" 
                       newTaskDescription = ""
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Lisää")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Listaus
        TaskList(
            tasks = tasks,
            onToggle = { task -> viewModel.toggleDone(task) },
            onTaskClick = { task ->
                selectedTask = task
                showDialog = true
            }
        )
    }
}

@Composable
fun TaskList(
    tasks: List<Task>,
    onToggle: (Task) -> Unit,
    onTaskClick: (Task) -> Unit
) {
    LazyColumn {
        items(tasks) { task ->
            TaskItem(task, onToggle, onTaskClick)
        }
    }
}

@Composable
fun TaskItem(
    task: Task, 
    onToggle: (Task) -> Unit, 
    onTaskClick: (Task) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        onClick = { onTaskClick(task) }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Checkbox vasemmalle
            Checkbox(
                checked = task.done,
                onCheckedChange = { onToggle(task) }
            )
            
            // Tekstit keskelle
            Column(modifier = Modifier.weight(1f).padding(start = 8.dp)) {
                Text(
                    text = task.title, 
                    fontSize = 20.sp,
                    textDecoration = if (task.done) androidx.compose.ui.text.style.TextDecoration.LineThrough else null
                )
                if (task.description.isNotBlank()) {
                     Text(text = task.description, fontSize = 14.sp)
                }
                Text(text = "Pvm: ${task.dueDate}", fontSize = 12.sp)
            }
        }
    }
}

