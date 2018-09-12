package com.gulowaty.quotes

import com.google.common.truth.Truth.assertThat
import com.google.gson.GsonBuilder
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post


@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
class StartQuoteE2E {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun startingQuoteReturnsUniqueReference() {
        val firstQuote = startNewQuote()
        val secondQuote = startNewQuote()

        assertThat(firstQuote.reference).isNotEqualTo(secondQuote.reference)
    }

    fun startNewQuote(): Quote {
        val rsp = mockMvc.perform(post("/quotes")).andReturn().response.contentAsString
        return GsonBuilder().create().fromJson(rsp, Quote::class.java)
    }

}
