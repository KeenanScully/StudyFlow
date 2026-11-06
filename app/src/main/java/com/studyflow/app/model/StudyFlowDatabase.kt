package com.studyflow.app.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ModuleEntity::class],
    version = 1,
    exportSchema = false
)
abstract class StudyFlowDatabase : RoomDatabase() {

    abstract fun moduleDao(): ModuleDao

}