package kr.ac.jejunu.line.db.entity

import androidx.room.*
import kr.ac.jejunu.line.db.database.MemoImageDao

@Entity(indices = [Index("memo_id")],
    foreignKeys = [ForeignKey(
        entity = Memo::class,
        parentColumns = ["id"],
        childColumns = ["memo_id"]
    )]
)
data class MemoImage(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "memo_id")
    var memoId: Int?,
    @ColumnInfo(name = "memo_image")
    var memoImage: String?
)