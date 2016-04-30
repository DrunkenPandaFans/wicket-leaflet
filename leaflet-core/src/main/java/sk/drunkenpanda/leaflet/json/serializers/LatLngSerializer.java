package sk.drunkenpanda.leaflet.json.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import sk.drunkenpanda.leaflet.models.LatLng;

import java.io.IOException;

public final class LatLngSerializer extends JsonSerializer<LatLng> {

    @Override
    public void serialize(LatLng value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeRaw("[" + value.getLatitude() + ", " + value.getLongitude() + "]");
    }
}
