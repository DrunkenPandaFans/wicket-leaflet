package sk.drunkenpanda.leaflet.models;

import java.io.Serializable;

/**
 * Layer used to load tiles on the map.
 *
 * @see <a href="http://leafletjs.com/reference.html#tilelayer">http://leafletjs.com/reference.html#tilelayer</a>
 */
public final class TileLayer implements ILayer, Serializable {

    private final String urlTemplate;

    private final TileLayerOptions options;

    /**
     * Creates new tile layer for the given url template and default options.
     * Url template must be in format specified at <a href="http://leafletjs.com/reference.html#url-template">http://leafletjs.com/reference.html#url-template</a>
     *
     * @param urlTemplate the map tiles template url.
     */
    public TileLayer(String urlTemplate) {
        this(urlTemplate, new TileLayerOptions());
    }

    public TileLayer(String urlTemplate, TileLayerOptions options) {
        this.urlTemplate = urlTemplate;
        this.options = options;
    }

    /**
     * @return map tiles template url.
     */
    public String getUrlTemplate() {
        return this.urlTemplate;
    }

    /**
     * @return configuration of tile layer.
     */
    public TileLayerOptions getOptions() {
        return this.options;
    }
}
