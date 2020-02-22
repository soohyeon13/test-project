package kr.ac.jejunu.line.ui.viewmodel.item

import android.app.Application
import androidx.lifecycle.MutableLiveData
import kr.ac.jejunu.line.base.BaseItemViewModel
import kr.ac.jejunu.line.base.BaseViewModel
import kr.ac.jejunu.line.db.entity.Memo

class MemoItemViewModel : BaseItemViewModel<Memo>() {
    val memo = MutableLiveData<Memo>()
    val memoTitle = MutableLiveData<String>()
    val memoThumbnails = MutableLiveData<String>()
    val memoContents = MutableLiveData<String>()

    override fun bind(data: Memo) {
        memo.value = data
        memoTitle.value = data.memoTitle
        memoThumbnails.value =  data.memoThumbnail
        memoContents.value = data.memoContents
    }
}