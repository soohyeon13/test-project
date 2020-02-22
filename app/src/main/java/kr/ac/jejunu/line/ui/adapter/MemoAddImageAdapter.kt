package kr.ac.jejunu.line.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.ac.jejunu.line.R
import kr.ac.jejunu.line.databinding.MemoImageItemBinding
import kr.ac.jejunu.line.db.entity.Image
import kr.ac.jejunu.line.ui.viewmodel.item.MemoImageItemViewModel

class MemoAddImageAdapter : RecyclerView.Adapter<MemoAddImageAdapter.ImageAddViewHolder>() {

    private var imageUri : List<Image>? = null

    class ImageAddViewHolder(val binding: MemoImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val viewModel = MemoImageItemViewModel()
        fun onImageBind(image: Image?) {
            viewModel.bind(image!!)
            binding.memoImage = viewModel
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageAddViewHolder {
        return ImageAddViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.memo_image_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
//       return if (::imageUri.isInitialized)  {
//            Log.d("test","count")
//            imageUri.size
//        } else {
//            Log.d("test","zero")
//            0
//        }
        Log.d("ttt", imageUri?.size.toString())
        return if (imageUri == null) 0 else imageUri!!.size
    }


    override fun onBindViewHolder(holder: ImageAddViewHolder, position: Int) {
        holder.onImageBind(imageUri?.get(position))
    }

    fun setImages(images : List<Image>?) {
        this.imageUri = images
//        if(::imageUri.isInitialized) {
//            Log.d("init","init")
//        } else {
//            Log.d("no init","no init")
//        }
        Log.d("test","image size : ${imageUri?.size}")
        notifyDataSetChanged()
    }

//    override fun getItemCount(): Int {
//        return imageUri.size
//    }
}