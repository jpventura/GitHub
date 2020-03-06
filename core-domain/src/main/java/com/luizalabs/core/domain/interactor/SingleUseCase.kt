package com.luizalabs.core.domain.interactor

import io.reactivex.Single

interface SingleUseCase<P, R> {

    operator fun invoke(parameter: P): Single<R>

}
