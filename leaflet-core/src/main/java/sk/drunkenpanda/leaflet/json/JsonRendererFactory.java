package sk.drunkenpanda.leaflet.json;

/**
 * Json renderer factory.
 */
public final class JsonRendererFactory {

    private static final JsonRenderer INSTANCE = new JsonRenderer();

    private JsonRendererFactory() {
    }

    /**
     *
     * @return instance of JSON renderer.
     */
    public static JsonRenderer getJsonRenderer() {
        return INSTANCE;
    }
}
