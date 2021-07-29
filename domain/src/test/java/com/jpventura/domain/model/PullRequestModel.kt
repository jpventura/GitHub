package com.jpventura.domain.model

import com.jpventura.core.domain.model.MemoryNestedModel
import com.jpventura.domain.VersionControlModel
import com.jpventura.domain.bean.PullRequest

class PullRequestModel : MemoryNestedModel<String, Int, PullRequest>(), VersionControlModel.PullModel
