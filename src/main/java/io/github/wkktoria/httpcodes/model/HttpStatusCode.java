package io.github.wkktoria.httpcodes.model;

public record HttpStatusCode(String code, String description) {
    @Override
    public String toString() {
        return "Status code: " + code() + "\n" + description();
    }
}
