package com.example.week1.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week1.model.Task
import com.example.week1.viewmodel.TaskViewModel

@Composable
fun CalendarScreen(viewModel: TaskViewModel = viewModel()) {
    // Jaettu tila: luetaan samat taskit kuin Homessa
    val tasks by viewModel.tasks.collectAsState()

    // Dialogin tila
    var showDialog by remember { mutableStateOf(false) }
    var selectedTask by remember { mutableStateOf<Task?>(null) }

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

    // Ryhmitellään tehtävät päivämäärän mukaan
    val groupedTasks = tasks.groupBy { it.dueDate }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Kalenteri", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))

        LazyColumn {
            groupedTasks.forEach { (date, tasksForDate) ->
                item {
                    // Otsikko päivälle
                    Text(
                        text = date.ifBlank { "Ei päivämäärää" },
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .padding(8.dp)
                    )
                }
                
                items(tasksForDate) { task ->
                    // Käytetään samaa TaskItemiä kuin Homessa
                    TaskItem(
                        task = task,
                        onToggle = { viewModel.toggleDone(it) },
                        onTaskClick = { 
                            selectedTask = it
                            showDialog = true
                        }
                    )
                }
            }
        }
    }
}
