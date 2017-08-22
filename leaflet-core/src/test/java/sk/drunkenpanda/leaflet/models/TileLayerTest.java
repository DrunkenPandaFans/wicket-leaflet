package sk.drunkenpanda.leaflet.models;

import org.junit.Test;

import com.google.common.collect.Lists;

import sk.drunkenpanda.leaflet.json.JsonRenderer;
import sk.drunkenpanda.leaflet.json.JsonRendererFactory;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jan Ferko
 */
public final class TileLayerTest {

    private final JsonRenderer renderer = JsonRendererFactory.getJsonRenderer();

    @Test
    public void testSerializeToJavascriptFunction() {
        final TileLayerOptions opts = TileLayerOptions.builder()
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
        final TileLayer layer = TileLayer.of("urtemplate.com/{x}/{y}", opts);

        final String expectedOpts = this.renderer.toJson(opts);
        final String expected = "L.tileLayer(\"urtemplate.com/{x}/{y}\", " + expectedOpts + ")";

        assertThat(this.renderer.toJson(layer)).isEqualTo(expected);
    }
}
