package com.studyflow.app.data.auth.api

import com.studyflow.app.model.Module
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ModuleApi {

    // Gets every module from the online StudyFlow API.
    @GET("api/Modules")
    suspend fun getModules(): List<Module>

    // Gets one module using its ID.
    @GET("api/Modules/{id}")
    suspend fun getModule(
        @Path("id") id: Int
    ): Module

    // Sends a new module to the API.
    @POST("api/Modules")
    suspend fun createModule(
        @Body module: Module
    ): Module

    // Updates an existing module.
    @PUT("api/Modules/{id}")
    suspend fun updateModule(
        @Path("id") id: Int,
        @Body module: Module
    ): Response<Unit>

    // Deletes a module from the database.
    @DELETE("api/Modules/{id}")
    suspend fun deleteModule(
        @Path("id") id: Int
    ): Response<Unit>
}