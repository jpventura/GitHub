package com.jpventura.data.ktx

import com.jpventura.domain.bean.*
import com.jpventura.data.entity.Issue as IssueEntity
import com.jpventura.data.entity.Owner as OwnerEntity
import com.jpventura.data.entity.Pull as PullEntity
import com.jpventura.data.entity.Repository as RepositoryEntity
import com.jpventura.data.entity.User as UserEntity
import com.jpventura.data.entity.issue as issueEntity
import com.jpventura.data.entity.owner as ownerEntity
import com.jpventura.data.entity.pullRequest as pullRequestEntity
import com.jpventura.data.entity.repository as repositoryEntity
import com.jpventura.data.entity.user as userEntity

fun Issue.asEntity(): IssueEntity = issueEntity {
    id = this@asEntity.id
    repositoryId = this@asEntity.parentKey
    body = this@asEntity.body
    createdAt = this@asEntity.createdAt
    id = this@asEntity.id
    number = this@asEntity.key
    title = this@asEntity.title
    url = this@asEntity.url
    user = this@asEntity.user.asEntity()
}

fun IssueEntity.asBean(repository: Repository): Issue = issue {
    id = this@asBean.id
    repositoryId = repository.key

    body = this@asBean.body
    createdAt = this@asBean.createdAt
    number = this@asBean.key
    repositoryId = repository.key
    title = this@asBean.title
    url = this@asBean.url
    user = this@asBean.user.asBean()
}

fun Owner.asEntity(): OwnerEntity = ownerEntity {
    avatarUrl = this@asEntity. avatarUrl
    id = this@asEntity.id
    key = this@asEntity.key
    name = this@asEntity. name
    reposUrl = this@asEntity. reposUrl
    userUrl = this@asEntity. userUrl
}

fun PullEntity.asBean(repository: Repository): PullRequest = pullRequest {
    id = this@asBean.id
    repositoryId = repository.key

    body = this@asBean.body
    createdAt = this@asBean.createdAt
    number = this@asBean.key
    repositoryId = repository.key
    state = this@asBean.state
    title = this@asBean.title
    user = repository.user
}

fun PullRequest.asEntity(): PullEntity = pullRequestEntity {
    id = this@asEntity.id
    repositoryId = this@asEntity.parentKey

    body = this@asEntity.body
    createdAt = this@asEntity.createdAt
    number = this@asEntity.key
    state = this@asEntity.state
    title = this@asEntity.title
    user = this@asEntity.user.asEntity()
}

fun OwnerEntity.asUser(): UserEntity = userEntity {
    avatarUrl = this@asUser.avatarUrl
    key = this@asUser.key
    name = this@asUser.name ?: ""
}

fun Repository.asEntity(): RepositoryEntity = repositoryEntity {
    description = this@asEntity.description
    forks = this@asEntity.forks
    id = this@asEntity.id
    language = this@asEntity.language
    name = this@asEntity.name
    owner = ownerEntity {
        avatarUrl = this@asEntity.owner.avatarUrl
        id = this@asEntity.owner.id
        key = this@asEntity.owner.key
        reposUrl = this@asEntity.owner.reposUrl
    }
    stargazersCount = this@asEntity.stargazersCount
}

fun User.asEntity(): UserEntity = userEntity {
    avatarUrl = this@asEntity.avatarUrl
    key = this@asEntity.key
    name = this@asEntity.name
}

fun Owner.asBean(): Owner = owner {
    avatarUrl = this@asBean. avatarUrl
    id = this@asBean.id
    key = this@asBean.key
    name = this@asBean. name
    reposUrl = this@asBean. reposUrl
    userUrl = this@asBean. userUrl
}

fun Owner.asUser(): User = user {
    avatarUrl = this@asUser.avatarUrl
    id = this@asUser.id
    key = this@asUser.key
    name = this@asUser.name ?: ""
}

fun RepositoryEntity.asBean(userEntity: UserEntity): Repository = repository {
    description = this@asBean.description
    forks = this@asBean.forks
    fullName = this@asBean.key
    id = this@asBean.id
    language = this@asBean.language
    name = this@asBean.name
    owner = owner {
        avatarUrl = this@asBean.owner.avatarUrl
        id = this@asBean.owner.id
        key = this@asBean.owner.key
        reposUrl = this@asBean.owner.reposUrl
    }
    stargazersCount = this@asBean.stargazersCount
    user = user {
        avatarUrl = userEntity.avatarUrl
        id = userEntity.id
        key = userEntity.key
        name = userEntity.name
    }
}

fun UserEntity.asBean(): User = user {
    avatarUrl = this@asBean.avatarUrl
    id = this@asBean.id
    key = this@asBean.key
    name = this@asBean.name
}
