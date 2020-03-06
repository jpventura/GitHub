package com.luizalabs.core.domain.interactor

import io.reactivex.Completable

interface CompletableUseCase {

    operator fun invoke(): Completable

}
