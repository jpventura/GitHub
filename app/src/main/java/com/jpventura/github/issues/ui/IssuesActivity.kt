package com.jpventura.github.issues.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import com.jpventura.domain.bean.Issue
import com.jpventura.github.R
import com.jpventura.github.core.ui.InjectedActivity
import com.jpventura.github.issues.IssuesContract
import com.jpventura.github.issues.ui.IssuesFragment.Companion.KEY_REPOSITORY_KEY
import com.jpventura.github.issues.ui.IssuesFragment.Companion.KEY_REPOSITORY_NAME
import org.kodein.di.Kodein

class IssuesActivity : InjectedActivity(), IssuesContract.Router {

    override fun activityModule() = Kodein.Module("emptyModule") {
    }

    override fun goToBrowser(issue: Issue) {
        val intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse(issue.url)
        }
        startActivity(intent)
    }

    override fun goToRepositoriesView() {
        onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_issues)

        title = intent.getStringExtra(KEY_REPOSITORY_NAME) ?: getString(R.string.app_name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.subtitle = "asdfasdf"

        if (savedInstanceState == null) {
            val repositoryId = intent.getStringExtra(KEY_REPOSITORY_KEY) ?: ""

            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.container,
                    IssuesFragment.newInstance(repositoryId),
                    IssuesFragment.TAG
                )
                .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            onBackPressed()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}
