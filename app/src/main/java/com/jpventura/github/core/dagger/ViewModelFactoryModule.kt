package com.jpventura.github.core.dagger

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @ActivityScope
    @Binds
    abstract fun bind(factory: ViewModelFactory): ViewModelProvider.Factory
}
