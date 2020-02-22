package kr.ac.jejunu.line.ui.adapter

import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kr.ac.jejunu.line.db.entity.Image
import kr.ac.jejunu.line.db.entity.Memo
import kr.ac.jejunu.line.ui.viewmodel.MainFragmentViewModel
import java.io.File


@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("imageUri")
fun setImageUri(view: ImageView, uri: Uri) {
    Log.d("test Uri" , uri.toString())
    val testUri = Uri.parse(uri.toString())
        Glide
            .with(view.context)
            .load(testUri)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(view)
}

//
@BindingAdapter(value = ["memoItems", "viewModel"])
fun setMemoItems(view: RecyclerView, items: List<Memo>?, vm: MainFragmentViewModel?) {
    Log.d("ttt", items.toString())
    view.adapter?.run {
        if (this is MemoAdapter) this.setMemos(items)
    }
    view.adapter?.notifyDataSetChanged()
}