package kr.ac.jejunu.line.ui.viewmodel

import android.app.Application
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kr.ac.jejunu.line.base.BaseViewModel
import kr.ac.jejunu.line.db.database.MemoRepository
import kr.ac.jejunu.line.db.entity.Memo
import kr.ac.jejunu.line.ui.adapter.MemoAdapter
import kr.ac.jejunu.line.util.SingleLiveEvent

class MainFragmentViewModel(application: Application) : BaseViewModel<List<Memo>>(application){
    var memoAdapter = MemoAdapter()
    private val memoRepository : MemoRepository by lazy {
        MemoRepository(application)
    }

    val clickAdd = SingleLiveEvent<Unit>()

    init {
        getMemos()
    }

    private fun getMemos() {
        addDisposable(memoRepository.getMemos(), dataObserver)
        Log.d("MainFragmentViewModel",onErrorEvent.call().toString())
    }

    fun clickAddMemo() {
        clickAdd.call()
    }

    override fun onRetrieveDataSuccess(t: List<Memo>) {
        memoAdapter.setMemos(t)
    }
}
