package sk.drunkenpanda.leaflet.models;

import java.io.Serializable;

public final class TileLayer implements ILayer, Serializable {

    private final String urlTemplate;

    private final TileLayerOptions options;

    public TileLayer(String urlTemplate) {
        this(urlTemplate, new TileLayerOptions());
    }

    public TileLayer(String urlTemplate, TileLayerOptions options) {
        this.urlTemplate = urlTemplate;
        this.options = options;
    }

    public String getUrlTemplate() {
        return this.urlTemplate;
    }

    public TileLayerOptions getOptions() {
        return this.options;
    }
}
