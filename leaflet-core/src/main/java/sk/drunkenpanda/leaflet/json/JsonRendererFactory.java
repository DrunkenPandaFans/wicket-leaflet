package sk.drunkenpanda.leaflet.json;

public final class JsonRendererFactory {

    private static final JsonRenderer INSTANCE = new JsonRenderer();

    private JsonRendererFactory() {
    }

    public static JsonRenderer getJsonRenderer() {
        return INSTANCE;
    }
}
