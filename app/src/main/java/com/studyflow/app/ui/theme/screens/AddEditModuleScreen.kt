package com.studyflow.app.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.studyflow.app.model.Module
import com.studyflow.app.viewmodel.ModuleViewModel

@Composable
fun AddEditModuleScreen(
    navController: NavController,
    moduleId: Int?,
    moduleViewModel: ModuleViewModel = viewModel()
) {

    var name by remember {
        mutableStateOf("")
    }

    var code by remember {
        mutableStateOf("")
    }

    var lecturer by remember {
        mutableStateOf("")
    }

    var colour by remember {
        mutableStateOf("")
    }

    var validationError by remember {
        mutableStateOf<String?>(null)
    }

    val selectedModule by
    moduleViewModel.selectedModule.collectAsState()

    val saveSuccessful by
    moduleViewModel.saveSuccessful.collectAsState()

    val apiError by
    moduleViewModel.errorMessage.collectAsState()

    // Loads the existing module when editing.
    LaunchedEffect(moduleId) {

        if (moduleId != null) {
            moduleViewModel.loadModule(
                moduleId,
                getModule = TODO()
            )
        }
    }

    // Places the existing module data into the form.
    LaunchedEffect(selectedModule) {

        selectedModule?.let {

            name = it.name
            code = it.code
            lecturer = it.lecturer
            colour = it.colour
        }
    }

    // Returns to Modules after the API confirms the save.
    LaunchedEffect(saveSuccessful) {

        if (saveSuccessful) {
            navController.popBackStack()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text =
                if (moduleId == null)
                    "Add Module"
                else
                    "Edit Module"
        )

        Spacer(
            modifier = Modifier.padding(8.dp)
        )

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
            },
            label = {
                Text("Module Name")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.padding(6.dp)
        )

        OutlinedTextField(
            value = code,
            onValueChange = {
                code = it
            },
            label = {
                Text("Module Code")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.padding(6.dp)
        )

        OutlinedTextField(
            value = lecturer,
            onValueChange = {
                lecturer = it
            },
            label = {
                Text("Lecturer")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.padding(6.dp)
        )

        OutlinedTextField(
            value = colour,
            onValueChange = {
                colour = it
            },
            label = {
                Text("Colour")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.padding(12.dp)
        )

        if (validationError != null) {

            Text(
                text = validationError ?: ""
            )

            Spacer(
                modifier = Modifier.padding(6.dp)
            )
        }

        if (apiError != null) {

            Text(
                text = apiError ?: ""
            )

            Spacer(
                modifier = Modifier.padding(6.dp)
            )
        }

        Button(
            onClick = {

                validationError = null

                if (
                    name.isBlank() ||
                    code.isBlank() ||
                    lecturer.isBlank() ||
                    colour.isBlank()
                ) {

                    validationError =
                        "Please complete all fields."

                    return@Button
                }

                val module = Module(
                    id = moduleId ?: 0,
                    name = name.trim(),
                    code = code.trim(),
                    lecturer = lecturer.trim(),
                    colour = colour.trim()
                )

                if (moduleId == null) {

                    moduleViewModel.addModule(module)

                } else {

                    moduleViewModel.updateModule(module)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text =
                    if (moduleId == null)
                        "Add Module"
                    else
                        "Save Changes"
            )
        }

        Spacer(
            modifier = Modifier.padding(6.dp)
        )

        Button(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cancel")
        }
    }
}