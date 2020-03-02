package com.jpventura.core.domain.interactor

import io.reactivex.Completable

interface CompletableUseCase {

    operator fun invoke(): Completable

}
