package kr.ac.jejunu.line.di

import kr.ac.jejunu.line.ui.viewmodel.MainFragmentViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel { MainFragmentViewModel(get()) }
}