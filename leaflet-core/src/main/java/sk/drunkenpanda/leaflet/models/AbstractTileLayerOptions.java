package sk.drunkenpanda.leaflet.models;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.immutables.value.Value;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.google.common.collect.Lists;

import sk.drunkenpanda.leaflet.json.ProperJson;

@ModelStyle
@ProperJson
@Value.Immutable
public abstract class AbstractTileLayerOptions implements Serializable {

    @Value.Default
    public int getMinZoom() {
        return 0;
    }

    @Value.Default
    public int getMaxZoom() {
        return 18;
    }

    @Nullable
    public abstract Integer getMaxNativeZoom();

    @Value.Default
    public int getTileSize() {
        return 256;
    }

    @Nonnull
    @Value.Default
    public List<String> getSubdomains() {
        return Lists.newArrayList("a", "b", "c");
    }

    @Nullable
    public abstract String getErrorTileUrl();

    @Nullable
    public abstract String getAttribution();

    @Value.Default
    public boolean isTms() {
        return false;
    }

    @Value.Default
    public boolean isContinuousWorld() {
        return false;
    }

    @Value.Default
    public boolean isNoWrap() {
        return false;
    }

    @Value.Default
    public int getZoomOffset() {
        return 0;
    }

    @Value.Default
    public boolean isZoomReverse() {
        return false;
    }

    @Value.Default
    public double getOpacity() {
        return 1.0;
    }

    @Nullable
    public abstract Double getZIndex();

    @Nullable
    public abstract Boolean getUnloadInvisibleTiles();

    @Nullable
    public abstract Boolean getUpdateWhenIdle();

    @Value.Default
    public boolean isDetectRetina() {
        return false;
    }

    @Value.Default
    public boolean isReuseTiles() {
        return false;
    }

    @Nullable
    public abstract LatLngBounds getBounds();

    @JsonAnyGetter
    public abstract Map<String, String> getExtraParameters();
}
