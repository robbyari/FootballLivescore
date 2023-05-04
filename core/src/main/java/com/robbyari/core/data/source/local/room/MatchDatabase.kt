package com.robbyari.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.robbyari.core.data.source.local.entity.MatchEntity

@Database(entities = [MatchEntity::class], version = 1, exportSchema = false)
abstract class MatchDatabase : RoomDatabase() {
    abstract fun matchDao(): MatchDao
}