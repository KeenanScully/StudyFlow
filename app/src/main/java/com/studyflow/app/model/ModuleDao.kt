package com.studyflow.app.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ModuleDao {

    // Observe modules belonging to a specific user
    @Query("""
        SELECT * FROM modules
        WHERE firebaseUid = :uid
        AND syncStatus != 'PENDING_DELETE'
        ORDER BY name ASC
    """)
    fun getModules(uid: String): Flow<List<ModuleEntity>>

    // Get a single local module
    @Query("""
        SELECT * FROM modules
        WHERE localId = :id
        LIMIT 1
    """)
    suspend fun getModuleByLocalId(id: Long): ModuleEntity?

    // Insert or update a local module
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertModule(module: ModuleEntity): Long

    // Insert multiple modules
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertModules(modules: List<ModuleEntity>)

    // Update a local module
    @Query("""
        UPDATE modules SET
        name = :name,
        code = :code,
        lecturer = :lecturer,
        colour = :colour,
        syncStatus = 'PENDING_UPDATE',
        updatedAt = :updatedAt
        WHERE localId = :localId
    """)
    suspend fun updateModule(
        localId: Long,
        name: String,
        code: String,
        lecturer: String,
        colour: String,
        updatedAt: Long = System.currentTimeMillis()
    )

    // Find modules waiting for synchronisation
    @Query("""
        SELECT * FROM modules
        WHERE firebaseUid = :uid
        AND syncStatus != 'SYNCED'
    """)
    suspend fun getPendingModules(uid: String): List<ModuleEntity>

    // Remove a local module permanently
    @Query("DELETE FROM modules WHERE localId = :localId")
    suspend fun deleteModule(localId: Long)

    // Mark a module as synchronised
    @Query("""
        UPDATE modules SET
        syncStatus = 'SYNCED'
        WHERE localId = :localId
    """)
    suspend fun markSynced(localId: Long)

}