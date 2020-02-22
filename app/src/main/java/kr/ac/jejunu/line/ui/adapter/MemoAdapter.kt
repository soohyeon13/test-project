package kr.ac.jejunu.line.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.ac.jejunu.line.R
import kr.ac.jejunu.line.databinding.MemoItemBinding
import kr.ac.jejunu.line.db.entity.Memo
import kr.ac.jejunu.line.ui.viewmodel.item.MemoItemViewModel

class MemoAdapter : RecyclerView.Adapter<MemoAdapter.MemoViewHolder>() {
    private lateinit var memo: List<Memo>
    class MemoViewHolder(val binding: MemoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = MemoItemViewModel()
        private lateinit var memos : Memo
        fun onBind(memo: Memo) {
            Log.d("test main adapter","test")
            viewModel.bind(memo)
            memos= memo
            binding.memoViewModel = viewModel
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        Log.d("test create viewholder","test")
        return MemoViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.memo_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = if (::memo.isInitialized) memo.size else 0

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        Log.d("adapter bind test","test")
        holder.onBind(memo[position])
    }

    fun setMemos(memos:List<Memo>?) {
        this.memo = memos!!
        notifyDataSetChanged()
    }

}