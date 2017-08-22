package sk.drunkenpanda.leaflet.models;

import org.junit.Test;

import com.google.common.collect.Lists;

import sk.drunkenpanda.leaflet.json.JsonRenderer;
import sk.drunkenpanda.leaflet.json.JsonRendererFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static sk.drunkenpanda.leaflet.FixtureHelpers.fixture;

/**
 * @author Jan Ferko
 */
public final class TileLayerOptionsTest {

    private JsonRenderer renderer = JsonRendererFactory.getJsonRenderer();

    private TileLayerOptions expected = TileLayerOptions.builder()
            .minZoom(0)
            .maxZoom(18)
            .tileSize(256)
            .subdomains(Lists.newArrayList("a", "b", "c"))
            .attribution("Map data &copy;")
            .zoomOffset(0)
            .opacity(1.0)
            .putExtraParameters("id", "<map_id>")
            .putExtraParameters("accessToken", "<access_token>")
            .isTms(false)
            .isContinuousWorld(false)
            .isNoWrap(false)
            .isZoomReverse(false)
            .isDetectRetina(false)
            .isReuseTiles(false)
            .bounds(LatLngBounds.of(
                        LatLng.of(50.0, 30.0),
                        LatLng.of(-50.0, -30.0)
            ))
            .build();

    @Test
    public void testRoundtrip() {
        final TileLayerOptions actual = renderer.fromJson(renderer.toJson(this.expected), TileLayerOptions.class);
        assertThat(actual).isEqualTo(this.expected);
    }

    @Test
    public void testDeserializeFromJson() {
        final TileLayerOptions decoded = renderer.fromJson(fixture("fixtures/tileLayerOptions.json"), TileLayerOptions.class);
        assertThat(decoded).isEqualTo(this.expected);
    }

    @Test
    public void testSerializeToJson() {
        final String fixture = renderer.toJson(
                renderer.fromJson(fixture("fixtures/tileLayerOptions.json"), TileLayerOptions.class));
        final String encoded = renderer.toJson(this.expected);
        assertThat(encoded).isEqualTo(fixture);
    }
}
