package io.github.wkktoria.httpcodes;

import io.github.wkktoria.httpcodes.controller.ScraperController;
import io.github.wkktoria.httpcodes.model.HttpStatusCode;
import io.github.wkktoria.httpcodes.service.ScraperService;

import java.util.Optional;
import java.util.Scanner;

class HttpCodes {
    public static void main(String[] args) {
        final ScraperController scraperController = new ScraperController(new ScraperService());
        final String statusCode;

        if (args.length != 1) {
            try (final Scanner scanner = new Scanner(System.in)) {
                System.out.print("Enter status code: ");
                statusCode = scanner.nextLine();
            }
        } else {
            statusCode = args[0];
        }

        Optional<HttpStatusCode> httpStatusCode = scraperController.getHttpStatusCode(statusCode);

        if (httpStatusCode.isPresent()) {
            System.out.println(httpStatusCode);
        }
    }
}
