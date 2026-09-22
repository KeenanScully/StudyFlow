package com.studyflow.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studyflow.app.data.auth.repository.ModuleRepository
import com.studyflow.app.model.Module
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ModuleViewModel : ViewModel() {

    private val repository = ModuleRepository()

    private val _modules =
        MutableStateFlow<List<Module>>(emptyList())

    val modules: StateFlow<List<Module>> = _modules

    private val _selectedModule =
        MutableStateFlow<Module?>(null)

    val selectedModule: StateFlow<Module?> = _selectedModule

    private val _errorMessage =
        MutableStateFlow<String?>(null)

    val errorMessage: StateFlow<String?> = _errorMessage

    private val _saveSuccessful =
        MutableStateFlow(false)

    val saveSuccessful: StateFlow<Boolean> = _saveSuccessful

    // Loads all modules from the REST API.
    fun loadModules() {

        viewModelScope.launch {

            try {

                _errorMessage.value = null
                _modules.value = repository.getModules()

            } catch (e: Exception) {

                _errorMessage.value =
                    e.message ?: "Unable to load modules."
            }
        }
    }

    // Loads one module when the user chooses to edit it.
    fun loadModule(id: Int, getModule: ModuleRepository.(Int) -> Module?) {

        viewModelScope.launch {

            try {

                _errorMessage.value = null
                _selectedModule.value =
                    repository.getModule(id)

            } catch (e: Exception) {

                _errorMessage.value =
                    e.message ?: "Unable to load module."
            }
        }
    }

    // Adds a new module through the REST API.
    fun addModule(module: Module) {

        viewModelScope.launch {

            try {

                _errorMessage.value = null
                _saveSuccessful.value = false

                repository.createModule(module)

                _saveSuccessful.value = true

            } catch (e: Exception) {

                _errorMessage.value =
                    e.message ?: "Unable to add module."
            }
        }
    }

    // Updates an existing module through the REST API.
    fun updateModule(module: Module) {

        viewModelScope.launch {

            try {

                _errorMessage.value = null
                _saveSuccessful.value = false

                repository.updateModule(module)

                _saveSuccessful.value = true

            } catch (e: Exception) {

                _errorMessage.value =
                    e.message ?: "Unable to update module."
            }
        }
    }

    // Deletes a module from the REST API.
    fun deleteModule(id: Int) {

        viewModelScope.launch {

            try {

                _errorMessage.value = null

                repository.deleteModule(id)

                loadModules()

            } catch (e: Exception) {

                _errorMessage.value =
                    e.message ?: "Unable to delete module."
            }
        }
    }
}