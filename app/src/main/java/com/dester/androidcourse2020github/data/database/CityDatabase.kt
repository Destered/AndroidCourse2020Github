import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dester.androidcourse2020github.data.database.WeatherEntity
import com.dester.androidcourse2020github.data.repositories.CityDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(WeatherEntity::class), version = 1, exportSchema = false)
abstract class CityDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var cityDao = database.cityDao()

                    // Delete all content here.
                    cityDao.deleteAll()


                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: CityDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): CityDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CityDatabase::class.java,
                    "city_database"
                )
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}