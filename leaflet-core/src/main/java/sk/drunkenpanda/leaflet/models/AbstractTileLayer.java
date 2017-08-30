package sk.drunkenpanda.leaflet.models;

import java.io.Serializable;

import javax.annotation.Nonnull;

import org.immutables.value.Value;

/**
 * Represents layer of map tiles provided by map providers.
 *
 * @author Jan Ferko
 */
@ModelStyle
@Value.Immutable(builder = false)
public abstract class AbstractTileLayer implements ILayer, Serializable {

    /**
     * Template for url where map tiles are located.
     *
     * @return template for url where map tiles are located.
     */
    @Nonnull
    @Value.Parameter
    public abstract String getUrlTemplate();

    /**
     * Tile layer configuration options.
     *
     * @return tile layer configuration options
     */
    @Nonnull
    @Value.Parameter
    public abstract TileLayerOptions getOptions();
}
