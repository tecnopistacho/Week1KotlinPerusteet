package com.example.myapplication.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.domain.*

@Composable
fun HomeScreen() {

    var allTasks by remember { mutableStateOf(MockTasks) }
    var visibleTasks by remember { mutableStateOf(MockTasks) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "My Tasks",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Button(onClick = {
                val newTask = Task(
                    id = allTasks.size + 1,
                    title = "New Task",
                    description = "Created from button",
                    priority = 1,
                    dueDate = "13/01/2026",
                    done = false
                )
                allTasks = addTask(allTasks, newTask)
                visibleTasks = allTasks
            }) {
                Text("Add")
            }

            Button(onClick = {
                visibleTasks = sortByDueDate(visibleTasks)
            }) {
                Text("Sort")
            }

            Button(onClick = {
                visibleTasks = filterByDone(allTasks, true)
            }) {
                Text("Done")
            }

            Button(onClick = {
                visibleTasks = allTasks
            }) {
                Text("All")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Task list
        visibleTasks.forEach { task ->
            TaskRow(
                task = task,
                onToggle = {
                    allTasks = toggleDone(allTasks, task.id)
                    visibleTasks = toggleDone(visibleTasks, task.id)
                }
            )
        }
    }
}

@Composable
fun TaskRow(
    task: Task,
    onToggle: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column {
            Text(text = task.title, style = MaterialTheme.typography.bodyLarge)
            Text(text = task.description, style = MaterialTheme.typography.bodySmall)
            Text(text = "Due: ${task.dueDate}")
        }

        Button(onClick = onToggle) {
            Text(if (task.done) "Undo" else "Done")
        }
    }
}

