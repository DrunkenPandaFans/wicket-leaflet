package sk.drunkenpanda.leaflet.example;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.springframework.beans.factory.annotation.Value;

import sk.drunkenpanda.leaflet.components.map.Map;
import sk.drunkenpanda.leaflet.components.map.MapOptions;
import sk.drunkenpanda.leaflet.models.LatLng;
import sk.drunkenpanda.leaflet.models.TileLayer;
import sk.drunkenpanda.leaflet.models.TileLayerOptions;

public class HomePage extends AbstractLeafletExamplePage {
    private static final long serialVersionUID = 1L;

    @Value("${mapbox.access.token}")
    private String accessToken;

    @Value("${mapbox.map.id}")
    private String mapId;

    public HomePage(final PageParameters parameters) {
        super(parameters);

        final IModel<LatLng> center = new Model<LatLng>(new LatLng(51.505, -0.09));

        final TileLayerOptions tilesOptions = new TileLayerOptions()
                .setAttribution("Map data &copy;...")
                .setMaxZoom(18)
                .addExtraParameter("id", mapId)
                .addExtraParameter("accessToken", this.accessToken);
        final TileLayer tileLayer = new TileLayer("https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}", tilesOptions);

        final MapOptions options = new MapOptions()
                .setZoom(13)
                .setDoubleClickZoom(false)
                .addLayer(tileLayer);
        add(new Map("map", center, options));
    }
}
