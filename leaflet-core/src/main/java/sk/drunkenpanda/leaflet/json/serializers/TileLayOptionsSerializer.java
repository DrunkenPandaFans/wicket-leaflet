package sk.drunkenpanda.leaflet.json.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import sk.drunkenpanda.leaflet.models.TileLayerOptions;

import java.io.IOException;
import java.util.Map;

public final class TileLayOptionsSerializer extends JsonSerializer<TileLayerOptions> {
    @Override
    public void serialize(TileLayerOptions value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeStartObject();
        gen.writeObjectField("attribution", value.getAttribution());
        gen.writeObjectField("bounds", value.getBounds());
        gen.writeObjectField("errorTileUrl", value.getErrorTileUrl());
        gen.writeObjectField("maxNativeZoom", value.getMaxNativeZoom());
        gen.writeObjectField("maxZoom", value.getMaxZoom());
        gen.writeObjectField("minZoom", value.getMinZoom());
        gen.writeObjectField("opacity", value.getOpacity());
//        gen.writeObjectField("subdomains", value.getSubdomains());
        gen.writeObjectField("tileSize", value.getTileSize());
        gen.writeObjectField("unloadInvisibleTiles", value.getUnloadInvisibleTiles());
        gen.writeObjectField("updateWhenIdle", value.getUpdateWhenIdle());
        gen.writeObjectField("zIndex", value.getZIndex());
        gen.writeObjectField("zoomOffset", value.getZoomOffset());
        for (Map.Entry<String, String> e : value.getExtraParameters().entrySet()) {
            gen.writeObjectField(e.getKey(), e.getValue());
        }
        gen.writeEndObject();
    }
}
