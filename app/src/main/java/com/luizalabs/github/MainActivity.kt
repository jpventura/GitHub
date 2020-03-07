package com.luizalabs.github

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.luizalabs.github.repositories.RepositoriesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val args = Bundle().apply {
            putP
        }
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, RepositoriesFragment.newInstance())
                    .commitNow()
        }
    }
}
