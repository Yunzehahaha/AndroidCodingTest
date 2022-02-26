package com.example.jetcomposedemo.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jetcomposedemo.model.Record
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Record::class], version = 1)
abstract class MyDatabase: RoomDatabase() {
    abstract fun myDao(): Dao

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): MyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "my_database"
                )
                    .fallbackToDestructiveMigration()
//                    .addCallback(DatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

//    private class DatabaseCallback(
//        private val scope: CoroutineScope
//    ) : RoomDatabase.Callback() {
//
//        override fun onCreate(db: SupportSQLiteDatabase) {
//            super.onCreate(db)
//            INSTANCE?.let { database ->
//                scope.launch(Dispatchers.IO) {
//                    populateDatabase(database.myDao())
//                }
//            }
//        }
//
//        suspend fun populateDatabase(dao: MyDao) {
//            // Delete all content here.
//            dao.deleteAll()
//        }
//    }
}