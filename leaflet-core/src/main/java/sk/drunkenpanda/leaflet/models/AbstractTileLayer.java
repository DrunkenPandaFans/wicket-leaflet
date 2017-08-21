package sk.drunkenpanda.leaflet.models;

import java.io.Serializable;

import javax.annotation.Nonnull;

import org.immutables.value.Value;

@ModelStyle
@Value.Immutable(builder = false)
public abstract class AbstractTileLayer implements ILayer, Serializable {

    @Nonnull
    @Value.Parameter
    public abstract String getUrlTemplate();

    @Nonnull
    @Value.Parameter
    public abstract TileLayerOptions getOptions();
}
