package com.jpventura.domain.model

import com.jpventura.core.domain.model.MemoryModel
import com.jpventura.domain.VersionControlModel
import com.jpventura.domain.bean.User

class UserModel : MemoryModel<String, User>(), VersionControlModel.UserModel