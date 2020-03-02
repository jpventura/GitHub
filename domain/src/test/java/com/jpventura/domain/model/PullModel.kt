package com.jpventura.domain.model

import com.jpventura.core.domain.model.MemoryNestedModel
import com.jpventura.domain.bean.Pull

class PullModel : MemoryNestedModel<String, Int, Pull>(), VersionControlModel.PullModel