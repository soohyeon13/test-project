package kr.ac.jejunu.line.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivity<T : ViewDataBinding,VM:BaseViewModel<*>>
    :AppCompatActivity(){
    protected lateinit var binding : T
    protected lateinit var viewModel:VM
    protected abstract val layoutId : Int
    protected abstract fun getViewModel():Class<VM>
    protected abstract fun getBindingVariable():Int
    abstract fun initObserve()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,layoutId)
        this.viewModel = ViewModelProvider(this).get(getViewModel())
        binding.lifecycleOwner = this
        initObserve()
    }
}