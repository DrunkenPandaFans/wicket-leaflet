package sk.drunkenpanda.leaflet.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import sk.drunkenpanda.leaflet.json.serializers.LeafletJacksonModule;

public final class JsonRenderer {

    private final ObjectMapper jacksonMapper;

    public JsonRenderer() {
        this.jacksonMapper = createObjectMapper();
    }

    private ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new LeafletJacksonModule());

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.disable(SerializationFeature.WRITE_NULL_MAP_VALUES);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.disable(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        return mapper;
    }

    public String toJson(Object obj) {
        try {
            return jacksonMapper.writeValueAsString(obj);
        } catch (Exception ex) {
            throw new JsonException("Error while writing object [" + obj.getClass().getCanonicalName() + "] to JSON", ex);
        }
    }

    public <T> T fromJson(final String json, Class<T> clazz) {
        try {
            return jacksonMapper.readValue(json, clazz);
        } catch (Exception ex) {
            throw new JsonException("Error while reading object [" + clazz.getCanonicalName() + "] from JSON string ["
                + json + "].", ex);
        }
    }
}
