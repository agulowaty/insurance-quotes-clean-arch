package com.gulowaty.quotes.web

import com.gulowaty.quotes.Quote
import com.gulowaty.quotes.StartNewQuoteResponsePresenter
import com.gulowaty.quotes.UseCaseFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit


/**
 * This is controller component as defined for the outer layer of Clean Arch.
 * I think in ideal Clean Architecture implementation we'll need separate Controller module
 * adapting Web interface further up
 */
@RestController
class QuoteWebController(@Autowired val useCaseFactory: UseCaseFactory) {

    @PostMapping(path = ["/quotes"])
    @ResponseBody
    fun startQuote(): Quote {
        val presenter = StartNewQuoteWebPresenter()
        useCaseFactory.startNewQuoteUc(presenter).execute()
        return presenter.awaitResult()
    }

    class StartNewQuoteWebPresenter: StartNewQuoteResponsePresenter {
        private val response: CompletableFuture<Quote> = CompletableFuture()

        override fun awaitResult(): Quote {
            return response.get(2, TimeUnit.SECONDS)
        }

        override fun onQuoteStarted(quote: Quote) {
            response.complete(quote)
        }

    }
}

