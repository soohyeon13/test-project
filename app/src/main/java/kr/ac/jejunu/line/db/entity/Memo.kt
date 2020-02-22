package kr.ac.jejunu.line.db.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Memo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int,
    @ColumnInfo(name = "memo_title")
    var memoTitle : String,
    @ColumnInfo(name = "memo_thumbnail")
    var memoThumbnail : String,
    @ColumnInfo(name = "memo_contents")
    var memoContents : String
//    @Embedded
//    var images : MemoImage
)
