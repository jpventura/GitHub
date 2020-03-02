package com.jpventura.domain.model

import com.jpventura.core.domain.model.MemoryNestedModel
import com.jpventura.domain.bean.Issue

class IssueModel : MemoryNestedModel<String, Int, Issue>(), VersionControlModel.IssueModel