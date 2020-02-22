package kr.ac.jejunu.line.ui.viewmodel

import androidx.lifecycle.ViewModel
import kr.ac.jejunu.line.base.BaseViewModel
import kr.ac.jejunu.line.util.SingleLiveEvent

class BottomSheetViewModel :ViewModel() {

    val clickUrl = SingleLiveEvent<Unit>()
    val clickGetPicture = SingleLiveEvent<Unit>()
    val clickTakePicture = SingleLiveEvent<Unit>()
    val clickCancel = SingleLiveEvent<Unit>()

    fun urlClick() {
        clickUrl.call()
    }

    fun getPictureClick() {
        clickGetPicture.call()
    }

    fun takePictureClick() {
        clickTakePicture.call()
    }

    fun cancelClick() {
        clickCancel.call()
    }
}