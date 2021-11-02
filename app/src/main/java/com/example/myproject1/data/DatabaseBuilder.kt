package com.example.myproject1.data

import android.content.Context
import androidx.room.Room

object DatabaseBuilder {
    private var INSTANCE: ProjectBd? = null

    fun getInstance(context: Context): ProjectBd {
        if (INSTANCE == null) {
            synchronized(ProjectBd::class) {
                INSTANCE = buildRoomDB(context)
            }
        }
        return INSTANCE!!
    }

    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            ProjectBd::class.java,
            "ProjectDatabase"
        ).build()

}