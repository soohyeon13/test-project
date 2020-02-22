package kr.ac.jejunu.line.base

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import java.lang.ref.WeakReference

abstract class BaseViewHolder<N>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private lateinit var navigator: WeakReference<N>

    fun getNavigator(): N {
        return navigator.get()!!
    }
    fun setNavigator(navigator: N) {
        this.navigator = WeakReference(navigator)
    }
}