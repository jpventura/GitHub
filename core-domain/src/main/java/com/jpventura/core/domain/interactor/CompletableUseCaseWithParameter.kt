package com.jpventura.core.domain.interactor

import io.reactivex.Completable

interface CompletableUseCaseWithParameter<P> {

    operator fun invoke(parameter: P): Completable

}
