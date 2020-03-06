package com.luizalabs.core.domain.interactor

import io.reactivex.Observable

interface UseCase<R> {

    operator fun invoke(): Observable<R>

}
