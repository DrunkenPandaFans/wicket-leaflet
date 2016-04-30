package sk.drunkenpanda.leaflet.json.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import sk.drunkenpanda.leaflet.models.TileLayer;

import java.io.IOException;

public final class TileLayerSerializer extends JsonSerializer<TileLayer> {
    @Override
    public void serialize(TileLayer value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeRaw("L.tileLayer('" + value.getUrlTemplate() + "', ");
        gen.writeObject(value.getOptions());
        gen.writeRaw(")");
    }
}
