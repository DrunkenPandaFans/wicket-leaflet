package sk.drunkenpanda.leaflet.json;

/**
 * Exception caused by issues with JSON serialization/deserialization.
 */
public class JsonException extends RuntimeException {

    public JsonException(String message, Throwable cause) {
        super(message, cause);
    }
}
