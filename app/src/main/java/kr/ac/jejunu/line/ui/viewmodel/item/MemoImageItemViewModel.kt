package kr.ac.jejunu.line.ui.viewmodel.item

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import kr.ac.jejunu.line.base.BaseItemViewModel
import kr.ac.jejunu.line.db.entity.Image
import kr.ac.jejunu.line.db.entity.MemoImage
import kr.ac.jejunu.line.db.entity.StorageImage

class MemoImageItemViewModel : BaseItemViewModel<Image>() {
    var images = MutableLiveData<Image>()
    var imageUri = MutableLiveData<Uri>()

    override fun bind(data: Image) {
        images.value = data
        imageUri.value = data.images
    }
}