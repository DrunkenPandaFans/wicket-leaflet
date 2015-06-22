package sk.drunkenpanda.leaflet.json.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import sk.drunkenpanda.leaflet.models.LatLng;
import sk.drunkenpanda.leaflet.models.LatLngBounds;

import java.io.IOException;

public final class LatLngBoundsSerializer extends JsonSerializer<LatLngBounds> {

    @Override
    public void serialize(LatLngBounds value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        JsonSerializer<Object> latLngSerializer = serializers.findValueSerializer(LatLng.class);
        gen.writeRaw('[');
        latLngSerializer.serialize(value.getNorthEast(), gen, serializers);
        gen.writeRaw(',');
        latLngSerializer.serialize(value.getSouthWest(), gen, serializers);
        gen.writeRaw(']');
    }
}
