package com.vixiloc.guessnumber.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vixiloc.guessnumber.domain.model.GameSetting
import kotlinx.coroutines.flow.Flow

@Dao
interface GameSettingDao {
    @Query("SELECT * from game_setting where id = 1")
    fun getSetting(): Flow<GameSetting?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(gameSetting: GameSetting)

    @Update
    suspend fun update(gameSetting: GameSetting)
}