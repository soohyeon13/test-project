package kr.ac.jejunu.line.db.database

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import kr.ac.jejunu.line.db.entity.Memo

@Dao
interface MemoDao {
    @Query("SELECT * FROM memo")
    fun getMemos() : Single<List<Memo>>

//    @Transaction
//    @Query("SELECT * FROM memo")
//    fun getAllMemo() :Single<List<MemoWithImage>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setMemo(memo:Memo) : Completable

    @Delete
    fun deleteMemo(memo:Memo)

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun updateMemo(memo: Memo)
}