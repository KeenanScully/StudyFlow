package com.studyflow.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.studyflow.app.navigation.Routes
import com.studyflow.app.viewmodel.ModuleViewModel

@Composable
fun ModulesScreen(
    navController: NavController,
    moduleViewModel: ModuleViewModel = viewModel()
) {

    val modules by moduleViewModel.modules.collectAsState()
    val errorMessage by moduleViewModel.errorMessage.collectAsState()

    // Loads the latest modules whenever this screen opens.
    LaunchedEffect(Unit) {
        moduleViewModel.loadModules()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(
            text = "My Modules"
        )

        Spacer(
            modifier = Modifier.padding(8.dp)
        )

        Button(
            onClick = {
                navController.navigate(Routes.ADD_MODULE)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Module")
        }

        Spacer(
            modifier = Modifier.padding(8.dp)
        )

        if (errorMessage != null) {

            Text(
                text = errorMessage ?: ""
            )

            Spacer(
                modifier = Modifier.padding(8.dp)
            )
        }

        if (modules.isEmpty()) {

            Text(
                text = "No modules added yet."
            )

        } else {

            LazyColumn(
                verticalArrangement =
                    Arrangement.spacedBy(12.dp)
            ) {

                items(modules) { module ->

                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text(
                                text = module.name
                            )

                            Text(
                                text = "Code: ${module.code}"
                            )

                            Text(
                                text =
                                    "Lecturer: ${module.lecturer}"
                            )

                            Text(
                                text =
                                    "Colour: ${module.colour}"
                            )

                            Spacer(
                                modifier =
                                    Modifier.padding(8.dp)
                            )

                            Row(
                                horizontalArrangement =
                                    Arrangement.spacedBy(8.dp)
                            ) {

                                Button(
                                    onClick = {
                                        navController.navigate(
                                            Routes.editModule(
                                                module.id
                                            )
                                        )
                                    }
                                ) {
                                    Text("Edit")
                                }

                                Button(
                                    onClick = {
                                        moduleViewModel
                                            .deleteModule(module.id)
                                    }
                                ) {
                                    Text("Delete")
                                }
                            }
                        }
                    }
                }
            }
        }

        Spacer(
            modifier = Modifier.padding(8.dp)
        )

        Button(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back to Dashboard")
        }
    }
}