package kr.ac.jejunu.line.ui.viewmodel

import android.app.Application
import android.util.Log
import android.view.MenuItem
import kr.ac.jejunu.line.base.BaseViewModel
import kr.ac.jejunu.line.db.entity.Memo
import kr.ac.jejunu.line.ui.adapter.MemoAddImageAdapter
import kr.ac.jejunu.line.util.SingleLiveEvent

class MemoAddViewModel(application:Application) : BaseViewModel<List<Memo>>(application) {
    var imageAdpater = MemoAddImageAdapter()
    init {
        Log.d("ttt","mavm")
    }
}