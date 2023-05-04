package com.robbyari.core.di

import android.content.Context
import androidx.room.Room
import com.robbyari.core.data.source.local.room.MatchDao
import com.robbyari.core.data.source.local.room.MatchDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    val passphrase: ByteArray = SQLiteDatabase.getBytes("robbyari23football".toCharArray())
    val factory = SupportFactory(passphrase)

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MatchDatabase = Room.databaseBuilder(
        context,
        MatchDatabase::class.java, "Match.db"
    )
        .fallbackToDestructiveMigration()
        .openHelperFactory(factory)
        .build()

    @Provides
    fun providesMatchDao(database: MatchDatabase): MatchDao = database.matchDao()
}