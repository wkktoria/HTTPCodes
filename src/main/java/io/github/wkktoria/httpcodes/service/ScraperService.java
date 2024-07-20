package io.github.wkktoria.httpcodes.service;

import io.github.wkktoria.httpcodes.exception.ExternalProblemException;
import io.github.wkktoria.httpcodes.exception.InvalidStatusCodeException;
import io.github.wkktoria.httpcodes.model.HttpStatusCode;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ScraperService {
    private final static String URL = "https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/";
    private final static String QUERY_SELECTOR = ".section-content p";

    public HttpStatusCode get(final String code) throws InvalidStatusCodeException, ExternalProblemException {
        try {
            final Document document = Jsoup.connect(URL + code).get();
            final Elements elements = document.select(QUERY_SELECTOR);

            StringBuilder stringBuilder = new StringBuilder();

            for (Element element : elements) {
                stringBuilder.append(element.text());
            }
            return new HttpStatusCode(code, stringBuilder.toString());
        } catch (HttpStatusException e) {
            if (e.getStatusCode() == 404) {
                throw new InvalidStatusCodeException("Status code not found");
            } else {
                throw new ExternalProblemException("Could not get information about HTTP response status code");
            }
        } catch (IOException e) {
            throw new ExternalProblemException("Could not get information about HTTP response status code");
        }
    }
}
