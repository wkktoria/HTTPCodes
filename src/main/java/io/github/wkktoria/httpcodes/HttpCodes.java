package io.github.wkktoria.httpcodes;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Scanner;

class HttpCodes {
    public static void main(String[] args) throws InvalidStatusCodeException, ExternalProblemException {
        final int statusCode;

        if (args.length != 1) {
            try (final Scanner scanner = new Scanner(System.in)) {
                System.out.print("Enter HTTP status code: ");
                statusCode = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                throw new InvalidStatusCodeException("Status code must be an integer");
            }
        } else {
            try {
                statusCode = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                throw new InvalidStatusCodeException("Status code must be an integer");
            }
        }

        try {
            Document document = Jsoup.connect("https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/" + statusCode).get();
            Elements elements = document.select(".section-content p");

            for (Element element : elements) {
                System.out.println(element.text());
            }
        } catch (HttpStatusException e) {
            if (e.getStatusCode() == 404) {
                throw new InvalidStatusCodeException("Status code not found");
            } else {
                throw new ExternalProblemException("Could not get information");
            }
        } catch (IOException e) {
            throw new ExternalProblemException("Could not get information");
        }
    }

    static class InvalidStatusCodeException extends Exception {
        InvalidStatusCodeException(final String message) {
            super(message);
        }
    }

    static class ExternalProblemException extends Exception {
        ExternalProblemException(final String message) {
            super(message);
        }
    }
}
