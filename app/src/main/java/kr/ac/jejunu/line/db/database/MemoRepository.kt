package kr.ac.jejunu.line.db.database

import android.app.Application
import android.util.Log
import androidx.paging.DataSource
import io.reactivex.Completable
import io.reactivex.Single
import kr.ac.jejunu.line.db.entity.Memo
import kr.ac.jejunu.line.db.entity.MemoImage

class MemoRepository(application: Application) {
    private var memoDao:MemoDao
    private var memoImageDao : MemoImageDao

    init {
        val database = MemoDatabase.getInstance(application)!!
        if (database != null) Log.d("test",database.toString())
        memoDao = database.memoDataDao()
        memoImageDao = database.memoImageDataDao()
    }

    fun getMemos() : Single<List<Memo>> { return memoDao.getMemos() }
//    fun getAllMemos():Single<List<MemoWithImage>> { return memoDao.getAllMemo()}
    fun insertMemo(memo:Memo):Completable {return memoDao.setMemo(memo)}

    fun getImages(memoId:Int) : DataSource.Factory<Int,MemoImage> {return memoImageDao.getMemoImages(memoId)}
    fun insertImages(memoImage: MemoImage) : Completable { return memoImageDao.insertMemoImages()}
}