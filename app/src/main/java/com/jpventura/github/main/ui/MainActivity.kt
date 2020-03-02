package com.jpventura.github.main.ui

import android.content.Intent
import android.os.Bundle
import com.jpventura.domain.bean.Repository
import com.jpventura.github.R
import com.jpventura.github.core.ui.InjectedActivity
import com.jpventura.github.issues.ui.IssuesActivity
import com.jpventura.github.issues.ui.IssuesFragment.Companion.KEY_REPOSITORY_KEY
import com.jpventura.github.issues.ui.IssuesFragment.Companion.KEY_REPOSITORY_NAME
import com.jpventura.github.issues.ui.IssuesFragment.Companion.KEY_USER_NAME
import com.jpventura.github.main.MainContract
import com.jpventura.github.repositories.ui.RepositoriesFragment
import org.kodein.di.Kodein

class MainActivity : InjectedActivity(), MainContract.Router {

    override fun activityModule() = Kodein.Module("mainModule") {
    }

    override fun goToRepositoriesView() {
        onBackPressed()
    }

    override fun goToIssuesView(repository: Repository) {
        val intent = Intent(this, IssuesActivity::class.java).apply {
            putExtra(KEY_REPOSITORY_KEY, repository.key)
            putExtra(KEY_REPOSITORY_NAME, repository.name)
            putExtra(KEY_USER_NAME, repository.user.name)
        }
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.container,
                    RepositoriesFragment.newInstance(),
                    RepositoriesFragment.TAG
                )
                .commit()
        }
    }
}
