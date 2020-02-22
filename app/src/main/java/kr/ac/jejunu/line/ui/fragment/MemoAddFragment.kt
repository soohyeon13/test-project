package kr.ac.jejunu.line.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_memo_add.*
import kr.ac.jejunu.line.BR
import kr.ac.jejunu.line.R
import kr.ac.jejunu.line.base.BaseFragment
import kr.ac.jejunu.line.databinding.FragmentMemoAddBinding
import kr.ac.jejunu.line.ui.adapter.MemoAddImageAdapter
import kr.ac.jejunu.line.ui.viewmodel.MemoAddViewModel

class MemoAddFragment :BaseFragment<FragmentMemoAddBinding,MemoAddViewModel>(),
    BottomNavigationView.OnNavigationItemSelectedListener {
    override val layoutId: Int = R.layout.fragment_memo_add
    private val adapter = MemoAddImageAdapter()
    override fun initObserve() {
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
        binding.imageRecyclerView.layoutManager = GridLayoutManager(this.requireContext(),3)
//        binding.imageRecyclerView.adapter = adapter
        Log.d("ttt","iio")
    }

    override fun onResume() {
        super.onResume()
        Log.d("ttt","or")
    }

    override fun getViewModel(): Class<MemoAddViewModel> {
        return MemoAddViewModel::class.java
    }

    override fun getBindingVariable(): Int {
         return BR.addViewModel
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.camera -> {
                val bottomSheet = BottomSheetDialog()
                bottomSheet.show(parentFragmentManager,"bottomSheet")
            }
        }
        return true
    }
}