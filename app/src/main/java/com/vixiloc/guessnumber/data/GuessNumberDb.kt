package com.vixiloc.guessnumber.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vixiloc.guessnumber.data.dao.GameSettingDao
import com.vixiloc.guessnumber.domain.model.GameSetting

@Database(entities = [GameSetting::class], version = 1)
abstract class GuessNumberDb : RoomDatabase() {
    abstract fun gameDao(): GameSettingDao

    companion object {
        @Volatile
        private var INSTANCE: GuessNumberDb? = null

        @JvmStatic
        fun getDatabase(context: Context): GuessNumberDb {
            if (INSTANCE == null) {
                synchronized(GuessNumberDb::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        GuessNumberDb::class.java, "guess_number_db"
                    )
                        .build()
                }
            }
            return INSTANCE as GuessNumberDb
        }
    }
}