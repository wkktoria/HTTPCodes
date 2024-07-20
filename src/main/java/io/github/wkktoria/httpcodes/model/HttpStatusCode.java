package io.github.wkktoria.httpcodes.model;

public class HttpStatusCode {
    private final String code;
    private final String description;

    public HttpStatusCode(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Status code: " + getCode() + "\n" + getDescription();
    }
}
