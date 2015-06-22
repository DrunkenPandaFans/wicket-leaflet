package sk.drunkenpanda.leaflet.json.serializers;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import sk.drunkenpanda.leaflet.models.LatLng;
import sk.drunkenpanda.leaflet.models.LatLngBounds;
import sk.drunkenpanda.leaflet.models.TileLayer;
import sk.drunkenpanda.leaflet.models.TileLayerOptions;

public final class LeafletJacksonModule extends SimpleModule {

    public LeafletJacksonModule() {
        super("wicket-leaflet", new Version(0, 0, 1, null, "sk.drunkenpanda", "wicket-leaflet"));
        registerSerializers();
    }

    private void registerSerializers() {
        addSerializer(LatLng.class, new LatLngSerializer());
        addSerializer(LatLngBounds.class, new LatLngBoundsSerializer());
        addSerializer(TileLayer.class, new TileLayerSerializer());
        addSerializer(TileLayerOptions.class, new TileLayOptionsSerializer());
    }
}
