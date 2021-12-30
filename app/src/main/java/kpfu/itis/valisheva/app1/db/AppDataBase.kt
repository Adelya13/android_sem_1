package kpfu.itis.valisheva.app1.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [Task::class], version = 1)
@TypeConverters(Converter::class)
abstract class AppDataBase : RoomDatabase(){

    abstract fun taskDao(): TaskDao

    companion object {

        private const val DATABASE_NAME = "task.db"

        @Volatile
        private var instance: AppDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context?) = instance ?: synchronized(LOCK) {
            context?.let {
                buildDatabase(it).apply {
                    instance = this
                }
            }

        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDataBase::class.java, DATABASE_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
    }
}
