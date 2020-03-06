package com.luizalabs.core.domain.interactor

import io.reactivex.Observable

interface UseCaseWithParameter<P, R> {

    operator fun invoke(parameter: P): Observable<R>

}
