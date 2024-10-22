package io.github.wkktoria.httpcodes.cli;

import io.github.wkktoria.httpcodes.model.HttpStatusCode;
import io.github.wkktoria.httpcodes.scraper.HttpStatusCodeScraper;

import java.util.Optional;

public class App {
    private final String code;
    private final HttpStatusCodeScraper httpStatusCodeScraper = new HttpStatusCodeScraper();

    public App(final String code) {
        this.code = code;
    }

    public void run() {
        Optional<HttpStatusCode> httpStatusCode = httpStatusCodeScraper.get(code);
        httpStatusCode.ifPresent(System.out::println);
    }
}
