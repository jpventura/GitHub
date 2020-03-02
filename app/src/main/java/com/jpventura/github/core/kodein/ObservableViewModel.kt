package com.jpventura.github.core.kodein

import com.jpventura.github.core.ui.ObservableViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind

inline fun <reified T : ObservableViewModel> Kodein.Builder.bindViewModel(
    overrides: Boolean? = null
): Kodein.Builder.TypeBinder<T> {
    return bind<T>(T::class.java.simpleName, overrides)
}
