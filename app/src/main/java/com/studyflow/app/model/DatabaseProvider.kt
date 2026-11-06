package com.studyflow.app.model

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    @Volatile
    private var INSTANCE: StudyFlowDatabase? = null

    fun getDatabase(context: Context): StudyFlowDatabase {

        return INSTANCE ?: synchronized(this) {

            val instance = Room.databaseBuilder(
                context.applicationContext,
                StudyFlowDatabase::class.java,
                "studyflow_database"
            )
                .build()

            INSTANCE = instance
            instance
        }
    }
}