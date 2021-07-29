package com.jpventura.domain

import com.jpventura.core.domain.model.NestedPersistedModel
import com.jpventura.core.domain.model.PersistedModel
import com.jpventura.domain.bean.Issue
import com.jpventura.domain.bean.PullRequest
import com.jpventura.domain.bean.Repository
import com.jpventura.domain.bean.User

interface VersionControlModel {

    interface IssueModel : NestedPersistedModel<String, Int, Issue>

    interface PullModel : NestedPersistedModel<String, Int, PullRequest>

    interface RepositoryModel : PersistedModel<String, Repository>

    interface UserModel : PersistedModel<String, User>

}
