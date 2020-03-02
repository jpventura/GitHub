package com.jpventura.github.core.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    private val providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    /**
     * {@inheritDoc}
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val provider: Provider<ViewModel>? = providers[modelClass]
            ?: providers.asIterable().firstOrNull { modelClass.isAssignableFrom(it.key) }?.value
            ?: throw IllegalArgumentException("Unknown view-model class $modelClass")

        @Suppress("UNCHECKED_CAST")
        return provider?.get() as T
    }
}
