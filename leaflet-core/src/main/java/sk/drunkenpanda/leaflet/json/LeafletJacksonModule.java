package sk.drunkenpanda.leaflet.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;

import sk.drunkenpanda.leaflet.json.serializers.TileLayerSerializer;
import sk.drunkenpanda.leaflet.models.TileLayer;

/**
 * @author Jan Ferko
 */
public final class LeafletJacksonModule extends SimpleModule {

    public LeafletJacksonModule() {
        super("wicket-leaflet", new Version(0, 0, 1, null, "sk.drunkenpanda", "wicket-leaflet"));
        registerSerializers();
    }

    private void registerSerializers() {
        addSerializer(TileLayer.class, new TileLayerSerializer());
    }
}
