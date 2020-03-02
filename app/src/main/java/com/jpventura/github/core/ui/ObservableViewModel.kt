package com.jpventura.github.core.ui

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.ViewModel
import com.jpventura.github.core.vm.RxSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

/**
 * A {@link ViewModel} that implements {@link android.databinding.Observable} interface and provides
 * {@link #notifyPropertyChanged(int)} and {@link #notifyChange} methods.
 */
abstract class ObservableViewModel : ViewModel(), Observable {

    val rxSchedulers = RxSchedulers()

    var state: State = Uninitialized
        set(value) {
            notifyChange()
            field = value
        }

    @delegate:Transient
    private val callbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }

    /**
     * This is the job for all coroutines started by this ViewModel.
     *
     * Cancelling this job will cancel all coroutines started by this ViewModel.
     */
    val viewModelJob = Job()

    /**
     * This is the IO scope for all coroutines launched by MainViewModel.
     *
     * Since we pass viewModelJob, you can cancel all coroutines launched by ioScope by calling
     * viewModelJob.cancel()
     */
    val ioScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    /**
     * This is the disposable for all rx.Observables started by this ViewModel.
     *
     * Clear it view model will cancel all observables started by this ViewModel.
     */
    val disposables = CompositeDisposable()

    /**
     * {@inheritDoc}
     */
    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        this.callbacks.add(callback)
    }

    /**
     * {@inheritDoc}
     */
    override fun onCleared() {
        disposables.clear()
        viewModelJob.cancel()
        super.onCleared()
    }

    /**
     * {@inheritDoc}
     */
    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        this.callbacks.remove(callback)
    }

    /**
     * {@inheritDoc}
     */
    fun notifyChange() {
        callbacks.notifyChange(this, 0)
    }

    /**
     * {@inheritDoc}
     */
    fun notifyPropertyChanged(fieldId: Int) {
        callbacks.notifyCallbacks(this, fieldId, null)
    }
}
