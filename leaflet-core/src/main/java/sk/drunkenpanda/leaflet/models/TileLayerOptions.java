package sk.drunkenpanda.leaflet.models;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        this.extraParameters = new HashMap<>();
    }

    public int getMinZoom() {
        return minZoom;
    }

    public TileLayerOptions setMinZoom(int minZoom) {
        this.minZoom = minZoom;
        return this;
    }

    public int getMaxZoom() {
        return maxZoom;
    }

    public TileLayerOptions setMaxZoom(int maxZoom) {
        this.maxZoom = maxZoom;
        return this;
    }

    public Integer getMaxNativeZoom() {
        return maxNativeZoom;
    }

    public TileLayerOptions setMaxNativeZoom(Integer maxNativeZoom) {
        this.maxNativeZoom = maxNativeZoom;
        return this;
    }

    public int getTileSize() {
        return tileSize;
    }

    public TileLayerOptions setTileSize(int tileSize) {
        this.tileSize = tileSize;
        return this;
    }

    public List<String> getSubdomains() {
        return subdomains;
    }

    public TileLayerOptions addSubdomain(String subdomain) {
        this.subdomains.add(subdomain);
        return this;
    }

    public TileLayerOptions setSubdomain(List<String> subdomains) {
        this.subdomains.clear();
        this.subdomains.addAll(subdomains);
        return this;
    }

    public String getErrorTileUrl() {
        return errorTileUrl;
    }

    public TileLayerOptions setErrorTileUrl(String errorTileUrl) {
        this.errorTileUrl = errorTileUrl;
        return this;
    }

    public String getAttribution() {
        return attribution;
    }

    public TileLayerOptions setAttribution(String attribution) {
        this.attribution = attribution;
        return this;
    }

    public boolean isTms() {
        return tms;
    }

    public TileLayerOptions setTms(boolean tms) {
        this.tms = tms;
        return this;
    }

    public boolean isContinuousWorld() {
        return continuousWorld;
    }

    public TileLayerOptions setContinuosWorld(boolean continuousWorld) {
        this.continuousWorld = continuousWorld;
        return this;
    }

    public boolean isNoWrap() {
        return noWrap;
    }

    public TileLayerOptions setNoWrap(boolean noWrap) {
        this.noWrap = noWrap;
        return this;
    }

    public int getZoomOffset() {
        return zoomOffset;
    }

    public TileLayerOptions setZoomOffset(int zoomOffset) {
        this.zoomOffset = zoomOffset;
        return this;
    }

    public boolean isZoomReverse() {
        return zoomReverse;
    }

    public TileLayerOptions setZoomReverse(boolean zoomReverse) {
        this.zoomReverse = zoomReverse;
        return this;
    }

    public double getOpacity() {
        return opacity;
    }

    public TileLayerOptions setOpacity(double opacity) {
        this.opacity = opacity;
        return this;
    }

    public double getZIndex() {
        return zIndex;
    }

    public TileLayerOptions setZIndex(double zIndex) {
        this.zIndex = zIndex;
        return this;
    }

    public Boolean getUnloadInvisibleTiles() {
        return unloadInvisibleTiles;
    }

    public TileLayerOptions setUnloadInvisibleTiles(boolean unloadInvisibleTiles) {
        this.unloadInvisibleTiles = unloadInvisibleTiles;
        return this;
    }

    public Boolean getUpdateWhenIdle() {
        return updateWhenIdle;
    }

    public TileLayerOptions setUpdateWhenIdle(boolean updateWhenIdle) {
        this.updateWhenIdle = updateWhenIdle;
        return this;
    }

    public boolean isDetectRetina() {
        return detectRetina;
    }

    public TileLayerOptions setDetectRetina(boolean detectRetina) {
        this.detectRetina = detectRetina;
        return this;
    }

    public boolean isReuseTiles() {
        return reuseTiles;
    }

    public TileLayerOptions setReuseTiles(boolean reuseTiles) {
        this.reuseTiles = reuseTiles;
        return this;
    }

    public LatLngBounds getBounds() {
        return bounds;
    }

    public TileLayerOptions setBounds(LatLngBounds bounds) {
        this.bounds = bounds;
        return this;
    }

    public Map<String, String> getExtraParameters() {
        return this.extraParameters;
    }

    public TileLayerOptions addExtraParameter(String key, String value) {
        this.extraParameters.put(key, value);
        return this;
    }

    public TileLayerOptions setExtraParameters(Map<String, String> extraParameters) {
        this.extraParameters.clear();
        this.extraParameters.putAll(extraParameters);
        return this;
    }
}
