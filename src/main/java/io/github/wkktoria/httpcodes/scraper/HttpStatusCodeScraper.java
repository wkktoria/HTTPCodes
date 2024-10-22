package io.github.wkktoria.httpcodes.scraper;

import io.github.wkktoria.httpcodes.model.HttpStatusCode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpStatusCodeScraper {
    private final static Logger LOGGER = Logger.getLogger(HttpStatusCodeScraper.class.getName());
    private final static String URL = "https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/";
    private final static String QUERY_SELECTOR = ".section-content p";

    public Optional<HttpStatusCode> get(final String code)  {
        try {
            final Document document = Jsoup.connect(URL + code).get();
            final Elements elements = document.select(QUERY_SELECTOR);

            return Optional.of(new HttpStatusCode(code,
                    elements.getFirst().text()));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            return Optional.empty();
        }
    }
}
