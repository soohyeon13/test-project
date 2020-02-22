package kr.ac.jejunu.line.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.get

abstract class BaseFragment<T : ViewDataBinding, VM : BaseViewModel<*>>
    : Fragment() {
    protected lateinit var binding: T
    protected lateinit var viewModel: VM
    protected abstract val layoutId: Int
    protected abstract fun initObserve()
    protected abstract fun getViewModel(): Class<VM>
    protected abstract fun getBindingVariable(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewModel = ViewModelProvider(this).get(getViewModel())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
        initObserve()
    }

    private fun setUp() {
        binding.setVariable(getBindingVariable(), viewModel)
        binding.lifecycleOwner = this
    }

    companion object {
        fun <T : Fragment?> newInstance(fragment: T): T {
            val args = Bundle()
            fragment!!.arguments = args
            return fragment
        }
    }
}