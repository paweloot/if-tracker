package dev.pawelbanas.iftracker.core.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import dev.pawelbanas.iftracker.core.db.entity.MealData
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDataDao {

    @Query("select * from MealData order by date(firstMealTime) desc")
    fun getAll(): Flow<List<MealData>>

    @Query("select * from MealData where substr(firstMealTime, 0, 11) = :date")
    fun getByDateAsFlow(date: String): Flow<MealData?>

    @Query("select * from MealData where substr(firstMealTime, 0, 11) = :date")
    suspend fun getByDate(date: String): MealData?

    @Insert
    fun insert(mealData: MealData)

    @Update
    fun update(mealData: MealData)
}