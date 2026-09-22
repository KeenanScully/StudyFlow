package com.studyflow.app.data.auth.repository

import com.studyflow.app.data.auth.api.RetrofitInstance
import com.studyflow.app.model.Module

class ModuleRepository {

    private val api = RetrofitInstance.moduleApi

    // Retrieves modules from the online REST API.
    suspend fun getModules(): List<Module> {
        return api.getModules()
    }

    // Sends a new module to the REST API.
    suspend fun createModule(module: Module): Module {
        return api.createModule(module)
    }

    // Updates an existing module and confirms that the API accepted the change.
    suspend fun updateModule(module: Module): Boolean {

        val response = api.updateModule(module.id, module)

        return response.isSuccessful
    }

    // Deletes a module from the online database.
    suspend fun deleteModule(id: Int) {
        api.deleteModule(id)
    }
}