package dev.pawelbanas.iftracker.feature.mealdata.data

import dev.pawelbanas.iftracker.core.db.dao.MealDataDao
import dev.pawelbanas.iftracker.core.db.entity.MealData
import dev.pawelbanas.iftracker.core.di.Dispatchers
import dev.pawelbanas.iftracker.feature.mealdata.domain.MealDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class DatabaseMealDataRepository @Inject constructor(
    private val dispatchers: Dispatchers,
    private val mealDataDao: MealDataDao
) : MealDataRepository {

    override fun getAllMealData(): Flow<List<MealData>> = mealDataDao.getAll()

    override fun getTodayMealDataAsFlow(): Flow<MealData?> = mealDataDao.getByDateAsFlow(LocalDate.now().toDbDateFormat())

    override suspend fun getByDate(localDate: LocalDate): MealData? = withContext(dispatchers.io) {
        mealDataDao.getByDate(localDate.toDbDateFormat())
    }

    override suspend fun insert(mealData: MealData) = withContext(dispatchers.io) {
        mealDataDao.insert(mealData)
    }

    override suspend fun update(mealData: MealData) = withContext(dispatchers.io) {
        mealDataDao.update(mealData)
    }

    private fun LocalDate.toDbDateFormat() = format(DateTimeFormatter.ofPattern(DB_DATE_PATTERN))

    companion object {
        private const val DB_DATE_PATTERN = "yyyy-MM-dd"
    }
}