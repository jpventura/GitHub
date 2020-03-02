package com.jpventura.core.domain.interactor

import io.reactivex.Single

interface SingleUseCaseWithParameter<P, R> {

    operator fun invoke(parameter: P): Single<R>

}
