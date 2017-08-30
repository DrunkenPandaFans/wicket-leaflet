package sk.drunkenpanda.leaflet.json.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import sk.drunkenpanda.leaflet.models.TileLayer;

/**
 * @author Jan Ferko
 */
public final class TileLayerSerializer extends JsonSerializer<TileLayer> {

    @Override
    public void serialize(TileLayer value, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        gen.writeRaw("L.tileLayer(\"" + value.getUrlTemplate() + "\", ");
        gen.writeObject(value.getOptions());
        gen.writeRaw(")");
    }
}
