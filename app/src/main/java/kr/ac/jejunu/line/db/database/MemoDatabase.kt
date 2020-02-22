package kr.ac.jejunu.line.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kr.ac.jejunu.line.db.database.MemoDatabase.Companion.DB_VERSION
import kr.ac.jejunu.line.db.entity.Memo
import kr.ac.jejunu.line.db.entity.MemoImage

@Database(entities = [Memo::class, MemoImage::class], version = DB_VERSION)
abstract class MemoDatabase : RoomDatabase() {
    abstract fun memoDataDao(): MemoDao
    abstract fun memoImageDataDao(): MemoImageDao

    companion object {
        const val DB_VERSION = 2
        private const val DB_NAME = "memoDB.db"
        private var databaseInstance: MemoDatabase? = null

        @Synchronized
        fun getInstance(context: Context): MemoDatabase? {
            if (databaseInstance == null) {
                databaseInstance = Room.databaseBuilder(context.applicationContext,
                    MemoDatabase::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return databaseInstance
        }

        private val MIGRATION_1_TO_2 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {

            }

        }
    }
}
