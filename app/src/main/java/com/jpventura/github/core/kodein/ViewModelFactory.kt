package com.jpventura.github.core.kodein

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.jpventura.github.issues.vm.IssuesViewModel
import com.jpventura.github.repositories.vm.RepositoriesViewModel
import org.kodein.di.DKodein
import org.kodein.di.KodeinAware
import org.kodein.di.direct
import org.kodein.di.generic.instance

class ViewModelFactory(private val injector: DKodein) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = with(modelClass) {
        when {
            isAssignableFrom(IssuesViewModel::class.java) -> {
                IssuesViewModel(injector.instance())
            }
            isAssignableFrom(RepositoriesViewModel::class.java) -> {
                RepositoriesViewModel(injector.instance())
            }
            else -> throw IllegalArgumentException("Model not supported: ${modelClass.simpleName}")
        }
    } as T
}

inline fun <reified VM : ViewModel, T> T.viewModel(): Lazy<VM> where T : KodeinAware, T : Fragment {
    return lazy { ViewModelProviders.of(requireActivity(), direct.instance()).get(VM::class.java) }
}

inline fun <reified VM : ViewModel, T> T.viewModel(): Lazy<VM> where T : KodeinAware, T : FragmentActivity {
    return lazy { ViewModelProviders.of(this, direct.instance()).get(VM::class.java) }
}

inline fun <reified VM : ViewModel, T> T.viewModel(): Lazy<VM> where T : KodeinAware, T : AppCompatActivity {
    return lazy { ViewModelProviders.of(this, direct.instance()).get(VM::class.java) }
}
