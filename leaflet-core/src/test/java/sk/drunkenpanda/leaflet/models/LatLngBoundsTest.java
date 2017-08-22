package sk.drunkenpanda.leaflet.models;

import org.junit.Test;

import sk.drunkenpanda.leaflet.json.JsonRenderer;
import sk.drunkenpanda.leaflet.json.JsonRendererFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static sk.drunkenpanda.leaflet.FixtureHelpers.fixture;

/**
 * @author Jan Ferko
 */
public final class LatLngBoundsTest {

    private JsonRenderer renderer = JsonRendererFactory.getJsonRenderer();

    @Test
    public void testRoundtrip() {
        final LatLngBounds bounds = LatLngBounds.of(
            LatLng.of(10.0, 20.0),
            LatLng.of(-10.0, -20.0)
        );
        final LatLngBounds decoded = this.renderer.fromJson(this.renderer.toJson(bounds), LatLngBounds.class);
        assertThat(decoded).isEqualTo(bounds);
    }

    @Test
    public void testDeserializeFromJson() {
        final LatLngBounds bounds = LatLngBounds.of(
            LatLng.of(35.34,54.10),
            LatLng.of(-54.30, -87.10)
        );
        final LatLngBounds decoded = this.renderer.fromJson(fixture("fixtures/latLngBounds.json"), LatLngBounds.class);
        assertThat(decoded).isEqualTo(bounds);
    }

    @Test
    public void testSerializeToJson() {
        final String expected = this.renderer.toJson(
                this.renderer.fromJson(fixture("fixtures/latLngBounds.json"), LatLngBounds.class)
        );
        final LatLngBounds bounds = LatLngBounds.of(
                LatLng.of(35.34,54.10),
                LatLng.of(-54.30, -87.10)
        );

        assertThat(this.renderer.toJson(bounds)).isEqualTo(expected);
    }
}
