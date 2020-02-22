package kr.ac.jejunu.line.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kr.ac.jejunu.line.db.database.MemoRepository
import kr.ac.jejunu.line.util.SingleLiveEvent

abstract class BaseViewModel<D> protected constructor(application: Application) : AndroidViewModel(application) {
    private val disposables: CompositeDisposable = CompositeDisposable()
    val onErrorEvent : SingleLiveEvent<Throwable> = SingleLiveEvent()
    val isLoading : SingleLiveEvent<Boolean> = SingleLiveEvent()

    fun addDisposable(single:Single<*>,disposable: DisposableSingleObserver<*>) {
            disposables.add(single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(disposable as SingleObserver<Any>) as Disposable)
    }

    val dataObserver : DisposableSingleObserver<D>
        get() = object : DisposableSingleObserver<D>() {
            override fun onSuccess(t: D) {
                onRetrieveDataSuccess(t)
                isLoading.value = false
            }

            override fun onError(e: Throwable) {
                onErrorEvent.value = e
                isLoading.value = false
            }

        }

    protected open fun onRetrieveDataSuccess(t: D) { }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}