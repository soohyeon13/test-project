package kr.ac.jejunu.line.db.entity

import android.net.Uri

data class StorageImage(
    var image : List<Image>
)

data class Image(
    var images : Uri
)