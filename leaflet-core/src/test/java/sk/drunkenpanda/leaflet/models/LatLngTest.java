package sk.drunkenpanda.leaflet.models;

import org.junit.Test;

import sk.drunkenpanda.leaflet.json.JsonRenderer;
import sk.drunkenpanda.leaflet.json.JsonRendererFactory;

import static org.assertj.core.api.Assertions.*;
import static sk.drunkenpanda.leaflet.FixtureHelpers.*;

/**
 * @author Jan Ferko
 */
public final class LatLngTest {

    private JsonRenderer renderer = JsonRendererFactory.getJsonRenderer();

    @Test
    public void testRoundtrip() {
        final LatLng latLng = LatLng.of(34.0, 71.0);
        final LatLng decoded = renderer.fromJson(renderer.toJson(latLng), LatLng.class);
        assertThat(decoded).isEqualTo(latLng);
    }

    @Test
    public void testSerializeFromJson() {
        final String expected = renderer.toJson(
                renderer.fromJson(fixture("fixtures/latLng.json"), LatLng.class));
        final String encoded = renderer.toJson(LatLng.of(30.1, 45.2));
        assertThat(encoded).isEqualTo(expected);
    }

    @Test
    public void testDeserializeToJson() {
        final LatLng expected = LatLng.of(30.1, 45.2);
        final LatLng decoded = renderer.fromJson(fixture("fixtures/latLng.json"), LatLng.class);
        assertThat(decoded).isEqualTo(expected);
    }
}
