package kr.ac.jejunu.line.ui

import kr.ac.jejunu.line.BR
import kr.ac.jejunu.line.R
import kr.ac.jejunu.line.base.BaseActivity
import kr.ac.jejunu.line.databinding.ActivityMainBinding
import kr.ac.jejunu.line.ui.viewmodel.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding,MainViewModel>() {
    override val layoutId: Int = R.layout.activity_main
    override fun getViewModel(): Class<MainViewModel> { return MainViewModel::class.java }

    override fun getBindingVariable(): Int { return BR.mainViewModel }

    override fun initObserve() {
    }
}