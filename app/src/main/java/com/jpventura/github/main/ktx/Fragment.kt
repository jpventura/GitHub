package com.jpventura.github.main.ktx

import com.jpventura.github.issues.ui.IssuesActivity
import com.jpventura.github.issues.ui.IssuesFragment
import com.jpventura.github.main.ui.MainActivity
import com.jpventura.github.repositories.ui.RepositoriesFragment

fun IssuesFragment.activity(): IssuesActivity = requireActivity() as IssuesActivity

fun RepositoriesFragment.activity(): MainActivity = requireActivity() as MainActivity
