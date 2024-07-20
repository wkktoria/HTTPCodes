package io.github.wkktoria.httpcodes.controller;

import io.github.wkktoria.httpcodes.exception.ExternalProblemException;
import io.github.wkktoria.httpcodes.exception.InvalidStatusCodeException;
import io.github.wkktoria.httpcodes.model.HttpStatusCode;
import io.github.wkktoria.httpcodes.service.ScraperService;

import java.util.Optional;

public class ScraperController {
    private final ScraperService scraperService;

    public ScraperController(final ScraperService scraperService) {
        this.scraperService = scraperService;
    }

    public Optional<HttpStatusCode> getHttpStatusCode(final String code) {
        try {
            return Optional.ofNullable(scraperService.get(code));
        } catch (InvalidStatusCodeException | ExternalProblemException e) {
            System.err.println(e.getMessage());
            return Optional.empty();
        }
    }
}
