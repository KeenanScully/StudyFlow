package com.studyflow.app.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "modules",
    indices = [Index(value = ["firebaseUid"])]
)
data class ModuleEntity(

    @PrimaryKey(autoGenerate = true)
    val localId: Long = 0,

    val serverId: Int? = null,

    val firebaseUid: String,

    val name: String,
    val code: String,
    val lecturer: String,
    val colour: String,

    // SYNCED, PENDING_CREATE, PENDING_UPDATE, PENDING_DELETE
    val syncStatus: String = "SYNCED",

    val updatedAt: Long = System.currentTimeMillis()
)