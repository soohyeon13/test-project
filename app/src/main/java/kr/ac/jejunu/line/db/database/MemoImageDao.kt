package kr.ac.jejunu.line.db.database

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import io.reactivex.Completable
import kr.ac.jejunu.line.db.entity.MemoImage

@Dao
interface MemoImageDao {
    @Query("SELECT * FROM MemoImage WHERE memo_id LIKE :memoId")
    fun getMemoImages(memoId:Int) : DataSource.Factory<Int,MemoImage>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMemoImages(vararg memoImage: MemoImage) : Completable

    @Delete
    fun deleteMemoImage(memoImage: MemoImage)

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun updateMemoImage(vararg memoImage: MemoImage)
}