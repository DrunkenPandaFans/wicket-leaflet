package sk.drunkenpanda.leaflet.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.common.collect.Lists;

/**
 * The tile layer configuration.
 *
 * @author Jan Ferko
 * @see <a href="http://leafletjs.com/reference.html#tilelayer-options">http://leafletjs.com/reference.html#tilelayer-options</a>
 */
public class TileLayerOptions implements Serializable {

    private int minZoom;

    private int maxZoom;

    private Integer maxNativeZoom;

    private int tileSize;

    private List<String> subdomains;

    private String errorTileUrl;

    private String attribution;

    private boolean tms;

    private boolean continuousWorld;

    private boolean noWrap;

    private int zoomOffset;

    private boolean zoomReverse;

    private double opacity;

    private double zIndex;

    private Boolean unloadInvisibleTiles;

    private Boolean updateWhenIdle;

    private boolean detectRetina;

    private boolean reuseTiles;

    private LatLngBounds bounds;

    private Map<String, String> extraParameters;

    /**
     * Creates default tile layer options.
     */
    public TileLayerOptions() {
        this.minZoom = 0;
        this.maxZoom = 18;
        this.tileSize = 256;
        this.subdomains = Lists.newArrayList("a", "b", "c");
        this.tms = false;
        this.continuousWorld = false;
        this.noWrap = false;
        this.zoomOffset = 0;
        this.zoomReverse = false;
        this.opacity = 1.0;
        this.detectRetina = false;
        this.reuseTiles = false;
        this.extraParameters = new HashMap<String, String>();
    }

    /**
     * @return minimum zoom number.
     */
    public int getMinZoom() {
        return minZoom;
    }

    /**
     * Sets minimum zoom number.
     *
     * @param minZoom new minimum zoom number.
     * @return this instance for chaining.
     */
    public TileLayerOptions setMinZoom(int minZoom) {
        this.minZoom = minZoom;
        return this;
    }

    /**
     * @return maximum zoom number.
     */
    public int getMaxZoom() {
        return maxZoom;
    }

    /**
     * Sets new maximum zoom number.
     *
     * @param maxZoom the maximum zoom number.
     * @return this instance for chaining.
     */
    public TileLayerOptions setMaxZoom(int maxZoom) {
        this.maxZoom = maxZoom;
        return this;
    }

    /**
     * Maximum zoom number the tiles source has available. If it is specified,
     * the tiles on all zoom levels higher than maxNativeZoom will be loaded from maxNativeZoom level
     * and auto-scaled.
     *
     * @return the maximum zoom number the tiles source has available.
     */
    public Integer getMaxNativeZoom() {
        return maxNativeZoom;
    }

    /**
     * Sets maximum zoom number the tiles source has available.
     *
     * @param maxNativeZoom new maximum zoom number the tiles source has available
     * @return this instance for chaining.
     */
    public TileLayerOptions setMaxNativeZoom(Integer maxNativeZoom) {
        this.maxNativeZoom = maxNativeZoom;
        return this;
    }

    /**
     * @return tile size (width and height in pixels, assuming tiles are square).
     */
    public int getTileSize() {
        return tileSize;
    }

    /**
     * Sets new tiles size in pixels.
     *
     * @param tileSize new tiles size.
     * @return this instance for chaining.
     */
    public TileLayerOptions setTileSize(int tileSize) {
        this.tileSize = tileSize;
        return this;
    }

    /**
     * Subdomains of the tile service.
     *
     * @return subdomains of the tile service
     */
    public List<String> getSubdomains() {
        return subdomains;
    }

    /**
     * Adds subdomain of the tile service.
     *
     * @param subdomain new subdomain of the tile service
     * @return this instance for chaining
     */
    public TileLayerOptions addSubdomain(String subdomain) {
        this.subdomains.add(subdomain);
        return this;
    }

    /**
     * Sets subdomains of the tile service.
     * Any previously added subdomains are removed.
     *
     * @param subdomains list of tile service subdomains
     * @return this instance for chaining.
     */
    public TileLayerOptions setSubdomain(List<String> subdomains) {
        this.subdomains.clear();
        this.subdomains.addAll(subdomains);
        return this;
    }

    /**
     * @return URL to the tile image to show in place of the tile that failed to load.
     */
    public String getErrorTileUrl() {
        return errorTileUrl;
    }

    /**
     * Sets new URL to the tile image to show in place of the tile that failed to load.
     *
     * @param errorTileUrl URL to the tile image to show in place of the tile that failed to load.
     * @return this instance for chaining.
     */
    public TileLayerOptions setErrorTileUrl(String errorTileUrl) {
        this.errorTileUrl = errorTileUrl;
        return this;
    }

    /**
     * @return the string used by the attribution control, describes the layer data.
     */
    public String getAttribution() {
        return attribution;
    }

    /**
     * Sets new string used by the attribution control, describes the layer data.
     *
     * @param attribution the string used by the attribution control, describes the layer data.
     * @return this instance for chaining
     */
    public TileLayerOptions setAttribution(String attribution) {
        this.attribution = attribution;
        return this;
    }

    /**
     * If true, inverses Y axis numbering for tiles (turn this on for TMS services).
     *
     * @return whether this is TMS service or not
     */
    public boolean isTms() {
        return tms;
    }

    /**
     * Sets whether this service is TMS or not.
     * If true, inverses Y axis numbering for tiles.
     *
     * @param tms whether this service is TMS or not.
     * @return this instance for chaining
     */
    public TileLayerOptions setTms(boolean tms) {
        this.tms = tms;
        return this;
    }

    /**
     * Whether tiles are wrapped by world width and height.
     * If set to true, the tile coordinates won't be wrapped by world width (-180 to 180 longitude)
     * or clamped to lie within world height (-90 to 90).
     * Use this if you use Leaflet for maps that don't reflect the real world (e.g. game, indoor or photo maps).
     *
     * @return whether tiles are wrapped by world width and height.
     */
    public boolean isContinuousWorld() {
        return continuousWorld;
    }

    /**
     * Whether tiles are wrapped by world width and height.
     * If set to true, the tile coordinates won't be wrapped by world width (-180 to 180 longitude)
     * or clamped to lie within world height (-90 to 90).
     * Use this if you use Leaflet for maps that don't reflect the real world (e.g. game, indoor or photo maps).
     *
     * @param continuousWorld Whether tiles are wrapped by world width and height.
     * @return this instance for chaining
     */
    public TileLayerOptions setContinuosWorld(boolean continuousWorld) {
        this.continuousWorld = continuousWorld;
        return this;
    }

    /**
     * Whether the tiles are wrapped in world width.
     * If set to true, the tiles just won't load outside the world width (-180 to 180 longitude) instead of repeating.
     *
     * @return whether the tiles are wrapped in world width
     */
    public boolean isNoWrap() {
        return noWrap;
    }

    /**
     * Sets whether the tiles are wrapped in world with.
     * If set to true, the tiles just won't load outside the world width (-180 to 180 longitude) instead of repeating.
     *
     * @param noWrap whether the tiles are wrapped in world with.
     * @return this instance for chaining
     */
    public TileLayerOptions setNoWrap(boolean noWrap) {
        this.noWrap = noWrap;
        return this;
    }

    /**
     *
     * @return the zoom number used in tile URLs will be offset with this value.
     */
    public int getZoomOffset() {
        return zoomOffset;
    }

    /**
     *
     * @param zoomOffset the zoom number used in tile URLs will be offset with this value.
     * @return this instance for chaining.
     */
    public TileLayerOptions setZoomOffset(int zoomOffset) {
        this.zoomOffset = zoomOffset;
        return this;
    }

    /**
     *
     * @return If set to true, the zoom number used in tile URLs will be reversed (maxZoom - zoom instead of zoom).
     */
    public boolean isZoomReverse() {
        return zoomReverse;
    }

    /**
     * Sets whether the zoom number used in tile URLs will be reversed (maxZoom - zoom instead of zoom).
     *
     * @param zoomReverse If set to true, the zoom number used in tile URLs will be reversed (maxZoom - zoom instead of zoom).
     * @return this instance for chaining
     */
    public TileLayerOptions setZoomReverse(boolean zoomReverse) {
        this.zoomReverse = zoomReverse;
        return this;
    }

    /**
     *
     * @return the opacity of the tile layer.
     */
    public double getOpacity() {
        return opacity;
    }

    /**
     * Sets the opacity of the tile layer.
     *
     * @param opacity the opacity of the tile layer.
     * @return this instance for chaining.
     */
    public TileLayerOptions setOpacity(double opacity) {
        this.opacity = opacity;
        return this;
    }

    /**
     * The explicit zIndex of the tile layer. Not set by default.
     *
     * @return the explicit zIndex of the tile layer.
     */
    public double getZIndex() {
        return zIndex;
    }

    /**
     * Sets explicit zIndex of the tile layer.
     *
     * @param zIndex the explicit zIndex of the tile layer.
     * @return this instance for chaining
     */
    public TileLayerOptions setZIndex(double zIndex) {
        this.zIndex = zIndex;
        return this;
    }

    /**
     * Whether tiles that are not visible after panning are removed.
     * True by default on mobile WebKit, otherwise false.
     *
     * @return If true, all the tiles that are not visible after panning are removed (for better performance).
     */
    public Boolean getUnloadInvisibleTiles() {
        return unloadInvisibleTiles;
    }

    /**
     * Sets whether tiles that are not visible after panning are removed.
     *
     * @param unloadInvisibleTiles whether tiles that are not visible after panning are removed.
     * @return this instance for chaining
     */
    public TileLayerOptions setUnloadInvisibleTiles(boolean unloadInvisibleTiles) {
        this.unloadInvisibleTiles = unloadInvisibleTiles;
        return this;
    }

    /**
     * Whether new tiles are loaded during panning.
     * If false, new tiles are loaded during panning, otherwise only after it (for better performance).
     * true by default on mobile WebKit, otherwise false.
     *
     * @return whether new tiles are loaded during panning.
     */
    public Boolean getUpdateWhenIdle() {
        return updateWhenIdle;
    }

    /**
     * Sets whether new tiles are loaded during panning.
     * If false, new tiles are loaded during panning, otherwise only after it (for better performance).
     *
     * @param updateWhenIdle whether new tiles are loaded during panning.
     * @return this instance for chaining.
     */
    public TileLayerOptions setUpdateWhenIdle(boolean updateWhenIdle) {
        this.updateWhenIdle = updateWhenIdle;
        return this;
    }

    /**
     * Detect if user is on a retina display or not.
     *
     * If true and user is on a retina display, it will request four tiles of half the specified size
     * and a bigger zoom level in place of one to utilize the high resolution.
     *
     * @return detect if user is on a retina display or not.
     */
    public boolean isDetectRetina() {
        return detectRetina;
    }

    /**
     * Sets whether to detect if user is on a retina display or not.
     *
     * @param detectRetina detect if user is on a retina display or not.
     * @return this instance for chaining
     */
    public TileLayerOptions setDetectRetina(boolean detectRetina) {
        this.detectRetina = detectRetina;
        return this;
    }

    /**
     * Whether the map should reuse tiles or not.
     *
     * If true, all the tiles that are not visible after panning are placed in a reuse queue from
     * which they will be fetched when new tiles become visible (as opposed to dynamically creating new ones).
     * This will in theory keep memory usage low and eliminate the need for reserving new memory whenever a new tile is needed.
     *
     * @return whether the map should reuse tiles or not.
     */
    public boolean isReuseTiles() {
        return reuseTiles;
    }

    /**
     * Sets whether the map should reuse tiles or not.
     *
     * @param reuseTiles whether the map should reuse tiles or not.
     * @return this instance for chaining.
     */
    public TileLayerOptions setReuseTiles(boolean reuseTiles) {
        this.reuseTiles = reuseTiles;
        return this;
    }

    /**
     * When this option is set, the TileLayer only loads tiles that are in the given geographical bounds.
     *
     * @return the tile layer geographical bounds or {@code null}.
     */
    public LatLngBounds getBounds() {
        return bounds;
    }

    /**
     * Sets tile layer geographical bounds.
     * When this option is set, the TileLayer only loads tiles that are in the given geographical bounds.
     *
     * @param bounds tile layer geographical bounds
     * @return this instance for chaining
     */
    public TileLayerOptions setBounds(LatLngBounds bounds) {
        this.bounds = bounds;
        return this;
    }

    /**
     *
     * @return extra parameters required by specific map tiles provider.
     */
    public Map<String, String> getExtraParameters() {
        return this.extraParameters;
    }

    /**
     * Adds any extra parameter required to render map tiles.
     *
     * @param key the parameter name
     * @param value the parameter value
     * @return this instance for chaining
     */
    public TileLayerOptions addExtraParameter(String key, String value) {
        this.extraParameters.put(key, value);
        return this;
    }

    /**
     * Sets extra parameters required to render map tiles.
     * Any previously set parameters are removed.
     *
     * @param extraParameters the map of extra parameters
     * @return this instance for chaining.
     */
    public TileLayerOptions setExtraParameters(Map<String, String> extraParameters) {
        this.extraParameters.clear();
        this.extraParameters.putAll(extraParameters);
        return this;
    }
}
