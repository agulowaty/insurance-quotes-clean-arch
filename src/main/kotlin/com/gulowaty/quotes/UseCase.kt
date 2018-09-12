package com.gulowaty.quotes

import java.util.*

interface UseCase {
    fun execute()
}

class UseCaseFactory {
    fun startNewQuoteUc(presenter: StartNewQuoteResponsePresenter): UseCase {
        return DefaultStartQuoteUseCase(presenter)
    }
}

/**
 * One flavour of presenter - just block waiting for the result
 * Other options may be RxJava or WebFlux - TODO
 * Now just simple implementation provided that just blocks on Future
 */
interface AwaitablePresenter<T> {
    fun awaitResult(): T

}

interface StartNewQuoteResponsePresenter : AwaitablePresenter<Quote> {
    fun onQuoteStarted(quote: Quote)
}


class DefaultStartQuoteUseCase(private val presenter: StartNewQuoteResponsePresenter) : UseCase {
    override fun execute() {
        presenter.onQuoteStarted(Quote(UUID.randomUUID().toString()))
    }
}
