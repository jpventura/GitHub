package com.jpventura.core.domain.interactor

import io.reactivex.Observable

interface UseCaseWithParameter<P, R> {

    operator fun invoke(param: P): Observable<R>

}
