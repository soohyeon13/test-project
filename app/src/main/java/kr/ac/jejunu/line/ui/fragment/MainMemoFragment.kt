package kr.ac.jejunu.line.ui.fragment

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kr.ac.jejunu.line.BR
import kr.ac.jejunu.line.R
import kr.ac.jejunu.line.base.BaseFragment
import kr.ac.jejunu.line.databinding.FragmentMainMemoBinding
import kr.ac.jejunu.line.ui.adapter.MemoAdapter
import kr.ac.jejunu.line.ui.viewmodel.MainFragmentViewModel
import kr.ac.jejunu.line.ui.viewmodel.MainViewModel

class MainMemoFragment : BaseFragment<FragmentMainMemoBinding, MainFragmentViewModel>() {
    override val layoutId: Int = R.layout.fragment_main_memo


    override fun getViewModel(): Class<MainFragmentViewModel> {
        return MainFragmentViewModel::class.java
    }

    override fun getBindingVariable(): Int {
        return BR.mainFragmentViewModel
    }

    override fun initObserve() {
        binding.memoList.layoutManager = StaggeredGridLayoutManager(2,RecyclerView.VERTICAL)
        with(viewModel) {
            clickAdd.observe(this@MainMemoFragment, Observer {
                val action = MainMemoFragmentDirections.actionMainMemoFragmentToMemoAddFragment()
                findNavController().navigate(action)
            })
        }
    }
}