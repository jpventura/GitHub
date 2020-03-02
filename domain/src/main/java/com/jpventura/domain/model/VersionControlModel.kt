package com.jpventura.domain.model

import com.jpventura.core.domain.model.NestedPersistedModel
import com.jpventura.core.domain.model.PersistedModel
import com.jpventura.domain.bean.Issue
import com.jpventura.domain.bean.Pull
import com.jpventura.domain.bean.Repository
import com.jpventura.domain.bean.User
import io.reactivex.Observable

interface VersionControlModel {

    interface IssueModel : NestedPersistedModel<String, Int, Issue>

    interface PullModel : NestedPersistedModel<String, Int, Pull>

    interface RepositoryModel : PersistedModel<String, Repository>

    interface UserModel : PersistedModel<String, User>

}
