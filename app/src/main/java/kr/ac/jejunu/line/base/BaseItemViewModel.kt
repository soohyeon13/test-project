package kr.ac.jejunu.line.base

import androidx.lifecycle.ViewModel

abstract class BaseItemViewModel<T> : ViewModel() {
    abstract fun bind(data:T)
}