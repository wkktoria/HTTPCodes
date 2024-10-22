package io.github.wkktoria.httpcodes.scraper;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class HttpStatusCodeScraperTest {
    private final HttpStatusCodeScraper sut = new HttpStatusCodeScraper();

    @Test
    void getReturnsOptional() {
        // given
        var code = "200";

        // when
        var result = sut.get(code);

        // then
        assertInstanceOf(Optional.class, result);
    }

    @Test
    void getReturnsAppropriateHttpStatusCodeWhenValidCodeIsProvided() {
        // given
        var code = "200";
        var expectedDescription = "The HTTP 200 OK successful response status code indicates that a request has succeeded. A 200 OK response is cacheable by default.";

        // when
        var result = sut.get(code);

        // then
        assertTrue(result.isPresent());
        assertEquals(code, result.get().code());
        assertEquals(expectedDescription, result.get().description());
    }

    @Test
    void getReturnsEmptyOptionalWhenInvalidCodeIsProvided() {
        // given
        var code = "0";

        // when
        var result = sut.get(code);

        // then
        assertTrue(result.isEmpty());
    }
}